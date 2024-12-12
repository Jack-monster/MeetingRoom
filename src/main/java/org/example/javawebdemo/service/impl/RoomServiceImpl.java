package org.example.javawebdemo.service.impl;

import lombok.Getter;
import org.apache.ibatis.session.SqlSession;
import org.example.javawebdemo.entity.MeetingRoom;
import org.example.javawebdemo.entity.Reservation;
import org.example.javawebdemo.mapper.MeetingRoomMapper;
import org.example.javawebdemo.mapper.ReservationMapper;
import org.example.javawebdemo.service.RoomService;
import org.example.javawebdemo.utils.MyBatisUtil;
import org.example.javawebdemo.utils.RoomUtil;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {

    private RoomServiceImpl(){}

    @Getter
    private static RoomService roomService = new RoomServiceImpl();

    @Override
    public Map<MeetingRoom, Boolean> getRoomMap() {
        HashMap<MeetingRoom, Boolean> resultMap = new LinkedHashMap<>();
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            MeetingRoomMapper mapper = sqlSession.getMapper(MeetingRoomMapper.class);
            HashSet<MeetingRoom> reservedSet = new HashSet<>(mapper.getReservedRoomList());
            List<MeetingRoom> roomList = mapper.getRoomList();
            roomList.forEach(room->{resultMap.put(room,reservedSet.contains(room));});
            return resultMap;
        }

    }

    @Override
    public int getRoomCount() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            MeetingRoomMapper mapper = sqlSession.getMapper(MeetingRoomMapper.class);
            return mapper.getRoomList().size();
        }
    }



    @Override
    public List<MeetingRoom> getAvailableRoomList() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)) {
            MeetingRoomMapper mapper = sqlSession.getMapper(MeetingRoomMapper.class);
            List<MeetingRoom> reservedRoomList = mapper.getReservedRoomList();
            List<MeetingRoom> roomList = mapper.getRoomList();
            return roomList.stream()
                            .filter(room -> !reservedRoomList.contains(room))
                             .collect(Collectors.toList());
        }
    }

    @Override
    public List<MeetingRoom> getReservableRoomList(int attendees, String reservationDate, LocalTime startHour, LocalTime endHour) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            MeetingRoomMapper roomMapper = sqlSession.getMapper(MeetingRoomMapper.class);
            ReservationMapper reservationMapper = sqlSession.getMapper(ReservationMapper.class);

            List<Reservation> approvedReservationListOftheDay = reservationMapper
                    .getApprovedReservationListOftheDay(reservationDate);


            return roomMapper.getRoomList().stream()
                    .filter(room -> {
                        if(room.getCapacity() >= attendees){

                            List<Reservation> reservations = approvedReservationListOftheDay
                                    .stream()
                                    .filter(reservation -> reservation.getRoom_id() == room.getRid())
                                    .collect(Collectors.toList());
                            for (Reservation reservation : reservations) {
                                Boolean overLapped = RoomUtil.isOverLapped(startHour, endHour, reservation);
                                if (overLapped) {
                                    return false;
                                }
                            }
                            return true;
                        }else{
                            return false;
                        }
                    })
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Boolean addNewRoom(String r_name, int capacity, String location, String image, String desc) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            MeetingRoomMapper mapper = sqlSession.getMapper(MeetingRoomMapper.class);
            return mapper.addNewRoom(r_name,capacity,location,image,desc) > 0;
        }
    }

    @Override
    public Boolean deleteRoomByRid(int rid) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            MeetingRoomMapper mapper = sqlSession.getMapper(MeetingRoomMapper.class);
            return mapper.deleteRoom(rid) > 0;
        }
    }


}
