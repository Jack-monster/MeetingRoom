package org.example.javawebdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    int reservation_id;
    int user_id;
    int room_id;
    LocalDate reservation_date;
    LocalTime start_time;
    LocalTime end_time;
    int attendees;
    String status;
    String apply_reason;
    String feedback_reason;
}
