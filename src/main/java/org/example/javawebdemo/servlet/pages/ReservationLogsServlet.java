package org.example.javawebdemo.servlet.pages;

import org.example.javawebdemo.entity.Reservation;
import org.example.javawebdemo.entity.User;
import org.example.javawebdemo.service.ReservationService;
import org.example.javawebdemo.service.impl.ReservationServiceImpl;
import org.example.javawebdemo.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/reservationLogs")
public class ReservationLogsServlet extends HttpServlet {

    private final ReservationService reservationService = ReservationServiceImpl.getReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        List<Reservation> reservationByUid = reservationService.getReservationByUid(user.getId());

        Context context = new Context();
        context.setVariable("reservation_list",reservationByUid);
        context.setVariable("nickname",user.getNickname());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ThymeleafUtil.getEngine().process("reservationLogs.html",context,resp.getWriter());


    }
}
