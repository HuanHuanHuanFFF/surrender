package com.hf.service;

import com.hf.pojo.User;

public interface UserService {
    boolean isIdRepeated(String id);

    boolean isNameRepeated(String name);

    void addUser(User user);

    User queryUserById(String id);
}
