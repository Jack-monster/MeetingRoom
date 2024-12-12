package org.example.javawebdemo.servlet.manage;

import org.example.javawebdemo.service.RoomService;
import org.example.javawebdemo.service.impl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-room")
public class DeleteRoomServlet extends HttpServlet {

    private final RoomService roomService = RoomServiceImpl.getRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rid = Integer.parseInt(req.getParameter("rid"));

        Boolean b = roomService.deleteRoomByRid(rid);

        resp.sendRedirect("indexAdmin");

    }
}
