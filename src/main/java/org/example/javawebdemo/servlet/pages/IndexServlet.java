package org.example.javawebdemo.servlet.pages;

import org.example.javawebdemo.entity.MeetingRoom;
import org.example.javawebdemo.entity.User;
import org.example.javawebdemo.service.ReservationService;
import org.example.javawebdemo.service.RoomService;
import org.example.javawebdemo.service.UserService;
import org.example.javawebdemo.service.impl.ReservationServiceImpl;
import org.example.javawebdemo.service.impl.RoomServiceImpl;
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
import java.util.ArrayList;
import java.util.Map;


@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private final RoomService roomService = RoomServiceImpl.getRoomService();
    private final UserService userService = UserServiceImpl.getUserService();
    private final ReservationService reservationService = ReservationServiceImpl.getReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Context context = new Context();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        context.setVariable("nickname",user.getNickname());

        Map<MeetingRoom, Boolean> roomMap = roomService.getRoomMap();
        context.setVariable("room_list",roomMap.keySet());
        context.setVariable("room_list_status",new ArrayList<>(roomMap.values()));

        context.setVariable("meeting_count",roomService.getRoomCount());
        context.setVariable("reserved_count",reservationService.getApprovedReservationCount());
        context.setVariable("user_count",userService.getUserCount());

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ThymeleafUtil.getEngine().process("index.html",context,resp.getWriter());

    }
}
