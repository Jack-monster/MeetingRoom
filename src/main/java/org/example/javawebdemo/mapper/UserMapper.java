package org.example.javawebdemo.mapper;

import org.apache.ibatis.annotations.*;
import org.example.javawebdemo.entity.User;

import java.util.List;


public interface UserMapper {

    @Select("select * from user")
    List<User> getUserList();

    @Select("select * from user where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    @Insert("insert into user (username,password,nickname) values(#{username},#{password},#{nickname})")
    int insertUser(@Param("username") String username, @Param("password") String password, @Param("nickname") String nickname);

    @Update("update user set password = #{newPassword} where username = #{username}")
    int updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);

    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);
}
