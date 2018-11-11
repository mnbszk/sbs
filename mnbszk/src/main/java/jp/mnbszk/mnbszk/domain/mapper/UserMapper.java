package jp.mnbszk.mnbszk.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import jp.mnbszk.mnbszk.domain.entity.User;

@Mapper
public interface UserMapper
{
    @Results(id="getAllResult", value= {
            @Result(property="userId", column="user_id", id=true),
            @Result(property="username", column="username"),
            @Result(property="password", column="password"),
            @Result(property="email", column="email"),
            @Result(property="roleName", column="role_name")
    })
    @Select("SELECT u.user_id, u.username, u.password, u.email, r.role_name"
            + " FROM users AS u"
            + " JOIN users_roles AS p USING(user_id)"
            + " JOIN roles AS r USING(role_id)")
    List<User> getAll();

    @Results(id="findByIdResult", value= {
            @Result(property="userId", column="user_id", id=true),
            @Result(property="username", column="username"),
            @Result(property="password", column="password"),
            @Result(property="email", column="email"),
            @Result(property="roleName", column="role_name")
    })
    @Select("SELECT u.user_id, u.username, u.password, u.email, r.role_name"
            + " FROM users AS u"
            + " JOIN users_roles AS p USING(user_id)"
            + " JOIN roles AS r USING(role_id)"
            + " WHERE user_id = #{id}")
    User findById(@Param("id") int id);

    @Results(id="findByUsernameResult", value= {
            @Result(property="userId", column="user_id", id=true),
            @Result(property="username", column="username"),
            @Result(property="password", column="password"),
            @Result(property="email", column="email"),
            @Result(property="roleName", column="role_name")
    })
    @Select("SELECT u.user_id, u.username, u.password, u.email, r.role_name"
            + " FROM users AS u"
            + " JOIN users_roles AS p USING(user_id)"
            + " JOIN roles AS r USING(role_id)"
            + " WHERE username = #{username}")
    User findByUsername(@Param("username") String username);
    
    @Insert("INSERT INTO users (username, password, email, created_at, updated_at) VALUES (#{username}, #{password}, #{email}, #{created_at}, #{updated_at})")
    @Options(useGeneratedKeys=true)
    void insert(User user);
}
