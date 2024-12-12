package org.example.javawebdemo.servlet.pages;

import org.example.javawebdemo.entity.User;
import org.example.javawebdemo.service.UserService;
import org.example.javawebdemo.service.impl.UserServiceImpl;
import org.example.javawebdemo.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userService.getUserList();
        Context context = new Context();

        context.setVariable("user_list",userList);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ThymeleafUtil.getEngine().process("users.html",context,resp.getWriter());
    }
}
