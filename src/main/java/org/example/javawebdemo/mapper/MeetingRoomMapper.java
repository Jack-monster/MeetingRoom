package org.example.javawebdemo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.javawebdemo.entity.MeetingRoom;

import java.util.List;

public interface MeetingRoomMapper {

    @Select("select * from meetingroom")
    List<MeetingRoom> getRoomList();

    @Select("select * from reservation,user,meetingroom where id = reservation.user_id and rid = reservation.room_id and start_time < CURRENT_TIME and end_time > CURRENT_TIME and reservation_date = CURRENT_DATE and status = 'approved'")
    List<MeetingRoom> getReservedRoomList();

    @Insert("insert into meetingroom (room_name, capacity, location, image, description) VALUES (#{room_name},#{capacity},#{location},#{image},#{desc})")
    int addNewRoom(@Param("room_name") String room_name, @Param("capacity") int capacity
            , @Param("location") String location, @Param("image") String image, @Param("desc") String desc);

    @Delete("delete from meetingroom where rid = #{rid} ")
    int deleteRoom(int rid);

}
