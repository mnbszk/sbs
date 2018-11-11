package jp.mnbszk.mnbszk.domain.entity;

import java.util.Date;

import lombok.Data;

//@Entity
@Data
public class User implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6927053567426386344L;

    private int userId;
    private String username;
    private String password;
    private String email;
    private String roleName;
    private Date createdAt;
    private Date updatedAt;
}