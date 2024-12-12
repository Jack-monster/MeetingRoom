package org.example.javawebdemo.servlet.manage;

import org.example.javawebdemo.entity.User;
import org.example.javawebdemo.service.RoomService;
import org.example.javawebdemo.service.impl.RoomServiceImpl;
import org.example.javawebdemo.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add-room")
public class AddRoomServlet extends HttpServlet {

    private final RoomService roomService = RoomServiceImpl.getRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        context.setVariable("nickname",user.getNickname());

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ThymeleafUtil.getEngine().process("add-room.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomName = req.getParameter("room_name");
        String desc = req.getParameter("desc");
        int capacity = Integer.parseInt(req.getParameter("capacity")) ;
        String image = req.getParameter("image");
        String location = req.getParameter("location");



        Boolean b = roomService.addNewRoom(roomName, capacity, location, image, desc);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (b) {
            out.print("{\"status\":\"success\", \"message\":\"会议室添加成功！\"}");
        } else {
            out.print("{\"status\":\"failure\", \"message\":\"会议室添加失败，可能存在服务器问题\"}");
        }
        out.flush();

    }
}
