package com.hf.servlet;

import com.alibaba.fastjson.JSONObject;
import com.hf.pojo.User;
import com.hf.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    ApplicationContext context;
    UserService userService;

    @Override
    public void init() throws ServletException {
        context = new ClassPathXmlApplicationContext("/test.xml");
        userService = (UserService) context.getBean("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = userService.queryUserById(id);
        Object json = JSONObject.toJSON(user);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();
    }
}
