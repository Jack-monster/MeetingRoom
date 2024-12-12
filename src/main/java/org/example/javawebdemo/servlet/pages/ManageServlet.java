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

@WebServlet("/manage")
public class ManageServlet extends HttpServlet {

    private final ReservationService reservationService = ReservationServiceImpl.getReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        context.setVariable("nickname",user.getNickname());

        List<Reservation> allReservation = reservationService.getAllReservation();
        context.setVariable("reservation_list",allReservation);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ThymeleafUtil.getEngine().process("manage.html",context,resp.getWriter());
    }
}
