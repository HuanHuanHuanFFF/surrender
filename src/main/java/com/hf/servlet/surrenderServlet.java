package com.hf.servlet;

import com.hf.pojo.User;
import com.hf.service.UserService;
import com.hf.utlis.RandomID;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/surrenderToAI")
public class surrenderServlet extends HttpServlet {
    ApplicationContext context;
    UserService userService;

    @Override
    public void init() throws ServletException {
        context = new ClassPathXmlApplicationContext("/spring.xml");
        userService = (UserService) context.getBean("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) context.getBean("user");
        String name = req.getParameter("name");
        String id = RandomID.getRandomID();
        while (userService.isIdRepeated(id)) {
            //确保ID不会重复
            id = RandomID.getRandomID();
        }
        userService.addUser(user);
        resp.getWriter().println("1");
    }
}