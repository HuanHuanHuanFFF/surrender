package com.hf.service.impl;

import com.hf.dao.UserDao;
import com.hf.pojo.User;
import com.hf.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isIdRepeated(String id) {
        return userDao.isIdRepeated(id);
    }

    @Override
    public boolean isNameRepeated(String name) {
        return userDao.isNameRepeated(name);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User queryUserById(String id) {
        return userDao.queryUserById(id);
    }
}
