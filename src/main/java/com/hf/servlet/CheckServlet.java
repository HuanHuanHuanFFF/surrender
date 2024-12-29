package com.hf.servlet;

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

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
    ApplicationContext context;
    UserService userService;

    @Override
    public void init() throws ServletException {
        context = new ClassPathXmlApplicationContext("/test.xml");
        userService = (UserService) context.getBean("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        if (userService.isNameRepeated(name)) {
            writer.print(1);
        } else writer.print(0);
        writer.flush();
        writer.close();
    }
}
