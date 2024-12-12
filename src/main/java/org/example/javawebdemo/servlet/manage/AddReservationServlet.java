package org.example.javawebdemo.servlet.manage;

import org.example.javawebdemo.entity.User;
import org.example.javawebdemo.service.ReservationService;
import org.example.javawebdemo.service.impl.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/add-reservation")
public class AddReservationServlet extends HttpServlet {

    private final ReservationService reservationService = ReservationServiceImpl.getReservationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String applyReason = req.getParameter("applyReason");
        int rid = Integer.parseInt(req.getParameter("rid"));
        LocalTime startHour = LocalTime.parse(req.getParameter("startHour") + ":00");
        LocalTime endHour = LocalTime.parse(req.getParameter("endHour") + ":00");
        int participants = Integer.parseInt(req.getParameter("participants"));
        LocalDate reservationDate = LocalDate.parse(req.getParameter("reservationDate"));
        User user = (User)req.getSession().getAttribute("user");

        Boolean success = reservationService.AddReservation(applyReason,rid,startHour,endHour,participants,reservationDate, user.getId());

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (success) {
            out.print("{\"status\":\"success\", \"message\":\"会议室预约成功！\"}");
        } else {
            out.print("{\"status\":\"failure\", \"message\":\"会议室预约失败，请重试。\"}");
        }
        out.flush();
    }
}
