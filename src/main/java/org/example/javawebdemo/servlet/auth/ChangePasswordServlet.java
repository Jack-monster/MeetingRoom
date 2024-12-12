package org.example.javawebdemo.servlet.auth;

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

@WebServlet("/change_password")
public class ChangePasswordServlet extends HttpServlet {

    private final UserService userService;

    public ChangePasswordServlet(){
        this.userService = UserServiceImpl.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        HttpSession session = req.getSession();
        if(session.getAttribute("failureNoExist") != null){
            context.setVariable("failureNoExist",true);
            session.removeAttribute("failureNoExist");
        }
        if(session.getAttribute("failureWrongPassword") != null){
            context.setVariable("failureWrongPassword",true);
            session.removeAttribute("failureWrongPassword");
        }
        if(session.getAttribute("failureSamePassword") != null){
            context.setVariable("failureSamePassword",true);
            session.removeAttribute("failureSamePassword");
        }
        if(session.getAttribute("failureBlankLine") != null){
            context.setVariable("failureBlankLine",true);
            session.removeAttribute("failureBlankLine");
        }
        if(session.getAttribute("SuccessChanged") != null){
            context.setVariable("SuccessChanged",true);
            session.removeAttribute("SuccessChanged");
        }


        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ThymeleafUtil.getEngine().process("change_password.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String old_password = req.getParameter("old_password");
        String new_password = req.getParameter("new_password");

        HttpSession session = req.getSession();
        User user = null;
        if(username.isEmpty() || old_password.isEmpty() || new_password.isEmpty()){
            session.setAttribute("failureBlankLine",true);
            resp.sendRedirect("change_password");
            return;
        }else if((user = userService.getUserByUsername(username)) == null){
            session.setAttribute("failureNoExist",true);
            resp.sendRedirect("change_password");
            return;
        }else if(!user.getPassword().equals(old_password)){
            session.setAttribute("failureWrongPassword",true);
            resp.sendRedirect("change_password");
            return;
        }else if(new_password.equals(old_password)){
            session.setAttribute("failureSamePassword",true);
            resp.sendRedirect("change_password");
            return;
        }else{
            if (userService.changePassword(username,new_password)){
                session.setAttribute("SuccessChanged",true);
                resp.sendRedirect("change_password");
                return;
            }
        }


    }
}
