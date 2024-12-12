package org.example.javawebdemo.filter;

import org.apache.ibatis.session.SqlSession;
import org.example.javawebdemo.entity.User;
import org.example.javawebdemo.mapper.UserMapper;
import org.example.javawebdemo.utils.MyBatisUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class MainFilter extends HttpFilter {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String uri = req.getRequestURI();
        if(uri.contains("static/") || uri.contains("/login") || uri.contains("/register") || uri.contains("/change_password")){
            chain.doFilter(req,res);
        }
        else{
            HttpSession session = req.getSession();
            User user = (User)session.getAttribute("user");
            if(user != null){
                chain.doFilter(req,res);
            }
            else{
                res.sendRedirect("login");
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
