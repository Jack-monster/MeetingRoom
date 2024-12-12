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

@WebServlet("/approve")
public class ApproveServlet extends HttpServlet {

    private final ReservationService reservationService = ReservationServiceImpl.getReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int reservationId = Integer.parseInt(req.getParameter("reservationId"));
        String feedbackReason = req.getParameter("feedback_reason");


        Boolean approvable = reservationService.isApprovable(reservationId);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (approvable) {
            Boolean b = reservationService.approveReservation(reservationId, feedbackReason);
            out.print("{\"status\":\"success\", \"message\":\"预约批准成功！\"}");
        } else {
            out.print("{\"status\":\"failure\", \"message\":\"预约批准失败，该会议室已被占用，请驳回预约。\"}");
        }
        out.flush();
    }
}
