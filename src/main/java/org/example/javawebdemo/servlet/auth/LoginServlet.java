package org.example.javawebdemo.servlet.auth;



import org.apache.ibatis.annotations.Param;
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


@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService;

    public LoginServlet(){
        this.userService = UserServiceImpl.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        Context context = new Context();
        HttpSession session = req.getSession();
        if(session.getAttribute("failure") != null){
            context.setVariable("failure",true);
            session.removeAttribute("failure");
        }


        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ThymeleafUtil.getEngine().process("login.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(!username.isEmpty() && !password.isEmpty()){
            User auth = userService.auth(username, password);
            HttpSession session = req.getSession();
            if(auth != null){
                session.setAttribute("user",auth);
                if(auth.getUsername().equals("admin")){
                    resp.sendRedirect("indexAdmin");
                }else {
                    resp.sendRedirect("index");
                }
                return;
            }else{
                session.setAttribute("failure",true);
                resp.sendRedirect("login");
                return;
            }
        }
        resp.sendRedirect("login");
    }
}
