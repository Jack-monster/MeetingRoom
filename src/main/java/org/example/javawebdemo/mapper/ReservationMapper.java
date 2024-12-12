package org.example.javawebdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.javawebdemo.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationMapper {

    @Select("select * from reservation")
    List<Reservation> getReservationList();

    @Select("select * from reservation where reservation_date = #{date} and status = 'approved'")
    List<Reservation> getApprovedReservationListOftheDay(String date);

    @Select("select * from reservation where user_id = #{uid}")
    List<Reservation> getReservationListByUid(int uid);

    @Select("select * from reservation where reservation_id = #{reservation_id}")
    Reservation getReservationByReservationId(int reservation_id);

    @Select("select * from reservation where status = 'approved' and reservation_date = #{date} and room_id = #{room_id}")
    List<Reservation> getApprovedReservationListByDayAndRoomId(@Param("date") LocalDate date,@Param("room_id") int room_id);

    @Insert("insert into reservation (apply_reason,room_id, start_time,end_time,attendees, reservation_date, user_id) " +
            "values (#{applyReason}, #{rid}, #{startHour}, #{endHour}, #{participants}, #{reservationDate}, #{user_id})")
    int addNewReservation(@Param("applyReason") String applyReason, @Param("rid") int rid,@Param("startHour") LocalTime startHour,@Param("endHour") LocalTime endHour,@Param("participants") int participants,@Param("reservationDate") LocalDate reservationDate,@Param("user_id") int user_id);

    @Update("UPDATE reservation SET status = #{status},feedback_reason = #{feedback_reason} WHERE reservation_id = #{reservation_id}")
    int alterReservationStatus( @Param("reservation_id") int reservation_id, @Param("status") String status, @Param("feedback_reason") String feedback_reason);



}
