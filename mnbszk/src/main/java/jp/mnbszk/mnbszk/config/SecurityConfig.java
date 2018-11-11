package jp.mnbszk.mnbszk.config;

import jp.mnbszk.mnbszk.auth.MnbszkAuthenticationFailureHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト
        // 静的リソースに対するアクセスは除外する
        web
            .ignoring()
            .antMatchers(
                "/favicon.ico",
                "/images/**",
                "/css/**",
                "/js/**",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
            // 認可の設定
            .authorizeRequests()
                .antMatchers("/", "/index", "/auth/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            // ログイン設定
            .formLogin()
                .loginPage("/auth/index")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard")
                .failureHandler(new MnbszkAuthenticationFailureHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            // ログアウト設定
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll();
    }
/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // https://stackoverflow.com/questions/49609768/spring-security-migrating-4-0-to-5-0-error-there-is-no-passwordencoder-mapp
        PasswordEncoder encoder = passwordEncoder();
        auth
            .inMemoryAuthentication()
                .withUser("mnbszk")
                .password(encoder.encode("secret"))
                .roles("USER")
            .and()
                .withUser("admin")
                .password(encoder.encode("secret"))
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/
    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter
    {
        @Autowired
        UserDetailsService userDetailsService;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
        
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // 認証するユーザーを設定する
            auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        }
    }
}
