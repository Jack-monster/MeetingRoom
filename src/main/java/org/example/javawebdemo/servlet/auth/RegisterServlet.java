package org.example.javawebdemo.servlet.auth;

import lombok.extern.java.Log;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final  UserService userService;

    public RegisterServlet(){
        this.userService = UserServiceImpl.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        HttpSession session = req.getSession();
        if(session.getAttribute("failureRegister") != null){
            context.setVariable("failureRegister",true);
            session.removeAttribute("failureRegister");
        }
        if(session.getAttribute("failureExit") != null){
            context.setVariable("failureExit",true);
            session.removeAttribute("failureExit");
        }


        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ThymeleafUtil.getEngine().process("register.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        if(!nickname.isEmpty() && !username.isEmpty() && !password.isEmpty()){
            User auth = userService.auth(username, password);
            if(auth == null){
                Boolean b = userService.registerUser(username, password, nickname);
                if(b){
                    session.setAttribute("user",userService.getUserByUsername(username));
                    resp.sendRedirect("index");
                    return;
                }
            }else{
                session.setAttribute("failureExit",true);
                resp.sendRedirect("register");
                return;
            }
        }else{
            session.setAttribute("failureRegister",true);
            resp.sendRedirect("register");
            return;
        }

    }
}
