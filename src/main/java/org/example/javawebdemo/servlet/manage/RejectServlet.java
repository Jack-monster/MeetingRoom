package org.example.javawebdemo.servlet.manage;

import org.example.javawebdemo.service.ReservationService;
import org.example.javawebdemo.service.impl.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reject")
public class RejectServlet extends HttpServlet {

    private final ReservationService reservationService = ReservationServiceImpl.getReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int reservationId = Integer.parseInt(req.getParameter("reservationId"));
        String feedbackReason = req.getParameter("feedback_reason");
        Boolean b = reservationService.rejectReservation(reservationId, feedbackReason);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (b) {
            out.print("{\"status\":\"success\", \"message\":\"预约驳回成功！\"}");
        } else {
            out.print("{\"status\":\"failure\", \"message\":\"预约驳回失败。\"}");
        }
        out.flush();
    }
}
