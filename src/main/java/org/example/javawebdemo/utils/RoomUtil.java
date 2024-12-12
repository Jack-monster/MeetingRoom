package org.example.javawebdemo.utils;

import org.example.javawebdemo.entity.Reservation;

import java.time.LocalTime;

public class RoomUtil {
    public static Boolean isOverLapped(LocalTime startHour, LocalTime endHour, Reservation reservation) {

        LocalTime existingStartTime = reservation.getStart_time();
        LocalTime existingEndTime = reservation.getEnd_time();

        // 检查时间段是否重叠
        return !(endHour.isBefore(existingStartTime) || startHour.isAfter(existingEndTime)) || (startHour.equals(existingStartTime) && endHour.equals(existingEndTime));
    }
}
