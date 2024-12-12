package org.example.javawebdemo.service;

import org.example.javawebdemo.entity.User;

import java.util.List;

public interface UserService {
    User auth(String username, String password);
    Boolean registerUser(String username, String password, String nickname);
    User getUserByUsername(String username);
    Boolean changePassword(String username, String new_password);
    int getUserCount();
    List<User> getUserList();
}
