package com.hf.dao;

import com.hf.pojo.User;

public interface UserDao {
    boolean isIdRepeated(String id);

    boolean isNameRepeated(String name);

    void addUser(User user);

    User queryUserById(String id);
}
