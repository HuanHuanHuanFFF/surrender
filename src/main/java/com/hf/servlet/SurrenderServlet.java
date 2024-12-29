package com.hf.servlet;

import com.alibaba.fastjson.JSONObject;
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
import java.io.PrintWriter;

@WebServlet("/surrender")
public class SurrenderServlet extends HttpServlet {
    ApplicationContext context;
    UserService userService;

    @Override
    public void init() throws ServletException {
        context = new ClassPathXmlApplicationContext("/test.xml");
        userService = (UserService) context.getBean("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        if (userService.isNameRepeated(name)) {
            writer.print(-1);
        } else if (name.length() > 30) {
            writer.print(-2);
        } else {
            User user = (User) context.getBean("user");
            String id = RandomID.getRandomID();
            while (userService.isIdRepeated(id)) {//确保ID不会重复
                id = RandomID.getRandomID();
            }
            user.setName(name);
            user.setId(id);
            userService.addUser(user);
            Object json = JSONObject.toJSON(user);
            writer.println(json);
        }
        writer.flush();
        writer.close();
    }
}