package jp.mnbszk.mnbszk.auth;

import org.springframework.security.core.authority.AuthorityUtils;

import jp.mnbszk.mnbszk.domain.entity.User;

public class LoginUser extends org.springframework.security.core.userdetails.User {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final User userEntity;
    
    public LoginUser(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRoleName()));
        this.userEntity = user;
    }
    
    public User getUser() {
        return userEntity;
    }
}
