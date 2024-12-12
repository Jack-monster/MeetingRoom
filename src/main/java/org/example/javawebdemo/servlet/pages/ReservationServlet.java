package org.example.javawebdemo.servlet.pages;

import org.example.javawebdemo.entity.MeetingRoom;
import org.example.javawebdemo.service.RoomService;
import org.example.javawebdemo.service.impl.RoomServiceImpl;
import org.example.javawebdemo.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reserve-room")
public class ReservationServlet extends HttpServlet {
    private final RoomService roomService = RoomServiceImpl.getRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MeetingRoom> availableRoomList = roomService.getAvailableRoomList();
        Context context = new Context();
        context.setVariable("available_room_list",availableRoomList);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ThymeleafUtil.getEngine().process("reserve.html",context, resp.getWriter());
    }
}
