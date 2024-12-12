package org.example.javawebdemo.service.impl;


import lombok.Getter;
import org.apache.ibatis.session.SqlSession;
import org.example.javawebdemo.entity.User;
import org.example.javawebdemo.mapper.UserMapper;
import org.example.javawebdemo.service.UserService;
import org.example.javawebdemo.utils.MyBatisUtil;

import java.util.Collections;
import java.util.List;


public class UserServiceImpl implements UserService {


    @Getter
    private final static UserService userService = new UserServiceImpl();

    private UserServiceImpl() {

    }


    @Override
    public User auth(String username, String password) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUser(username, password);
        }
    }

    @Override
    public Boolean registerUser(String username, String password, String nickname) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.insertUser(username,password,nickname) > 0;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUserByUsername(username);
        }
    }

    @Override
    public Boolean changePassword(String username, String new_password) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.updatePassword(username,new_password) > 0;
        }
    }

    @Override
    public int getUserCount() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUserList().size();
        }
    }

    @Override
    public List<User> getUserList() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUserList();
        }
    }
}
