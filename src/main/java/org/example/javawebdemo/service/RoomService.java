package org.example.javawebdemo.service;

import org.example.javawebdemo.entity.MeetingRoom;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface RoomService {
    Map<MeetingRoom,Boolean> getRoomMap();
    int getRoomCount();
    List<MeetingRoom> getAvailableRoomList();
    List<MeetingRoom> getReservableRoomList(int attendees, String reservationDate, LocalTime startHour, LocalTime endHour);
    Boolean addNewRoom(String r_name,int capacity, String location,String image,String desc );
    Boolean deleteRoomByRid(int rid);
}
