package com.hf;

import com.hf.dao.UserDao;
import com.hf.pojo.User;
import com.hf.service.UserService;
import com.hf.utlis.RandomID;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestA {

    @org.junit.Test
    public void test1() {
        System.out.println(RandomID.getRandomID());
    }

    @org.junit.Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring.xml");
        UserDao userDao1 = (UserDao) context.getBean("userDao");
        UserDao userDao2 = (UserDao) context.getBean("userDao");
        System.out.println(userDao1 == userDao2);
    }

    @org.junit.Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/test.xml");
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.isIdRepeated("1"));
    }

    @org.junit.Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/test.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
    }

    @org.junit.Test
    public void test5() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/test.xml");
        User user = (User) context.getBean("user");
        UserService userService = (UserService) context.getBean("userService");
        user.setId(RandomID.getRandomID());
        user.setName("test");
        userService.addUser(user);
    }

    @org.junit.Test
    public void test6() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/test.xml");
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.queryUserById("G1TZZ7-668K"));
    }
}