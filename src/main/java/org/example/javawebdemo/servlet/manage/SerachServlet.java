package org.example.javawebdemo.servlet.manage;

import com.google.gson.Gson;
import org.example.javawebdemo.entity.MeetingRoom;
import org.example.javawebdemo.service.RoomService;
import org.example.javawebdemo.service.impl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/search-rooms")
public class SerachServlet extends HttpServlet {

    private final RoomService roomService = RoomServiceImpl.getRoomService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int participants = Integer.parseInt(req.getParameter("participants"));
        String reservationDate = req.getParameter("reservationDate");
        String start = req.getParameter("startHour");
        LocalTime startHour = LocalTime.parse(start + ":00");
        String end = req.getParameter("endHour");
        LocalTime endHour = LocalTime.parse(end + ":00");
        List<MeetingRoom> reservableRoomList = roomService.getReservableRoomList(participants, reservationDate, startHour, endHour);

        String json = new Gson().toJson(reservableRoomList);

        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();

    }
}
