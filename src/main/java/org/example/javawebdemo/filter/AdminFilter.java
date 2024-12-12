package org.example.javawebdemo.filter;

import org.example.javawebdemo.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/indexAdmin","/manage","/users","/add-room"})
public class AdminFilter extends HttpFilter {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null)
        {
            if (user.getUsername().equals("admin")) {
                chain.doFilter(req, res);
            } else {
                res.sendRedirect("index");
                return;
            }
        }else{
            res.sendRedirect("login");
            return;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
