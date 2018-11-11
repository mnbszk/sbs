package jp.mnbszk.mnbszk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.mnbszk.mnbszk.auth.LoginUser;
import jp.mnbszk.mnbszk.domain.entity.User;
import jp.mnbszk.mnbszk.domain.mapper.UserMapper;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = null;
        try {
            // 入力したユーザーIDからに認証を行うユーザー情報を取得する
            user = userMapper.findByUsername(username);
        } catch (Exception e)  {
            throw new UsernameNotFoundException("It cannot be acquired user.");
        }
        
        if (user == null) {
            throw new UsernameNotFoundException("User not found for login id: " + username);
        }

        return new LoginUser(user);
    }

}
