package org.example.javawebdemo.service;

import org.example.javawebdemo.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ReservationService {
    Boolean AddReservation(String applyReason, int rid, LocalTime startHour, LocalTime endHour, int participants, LocalDate reservationDate, int user_id);
    List<Reservation> getAllReservation();
    List<Reservation> getReservationByUid(int user_id);
    Boolean approveReservation(int reservationId, String feedback_reason);
    Boolean rejectReservation(int reservationId, String feedback_reason);
    Boolean isApprovable(int reservationId);
    int getApprovedReservationCount();
}
