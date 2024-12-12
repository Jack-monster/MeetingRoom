package org.example.javawebdemo.service.impl;


import lombok.Getter;
import org.apache.ibatis.session.SqlSession;
import org.example.javawebdemo.entity.Reservation;
import org.example.javawebdemo.mapper.MeetingRoomMapper;
import org.example.javawebdemo.mapper.ReservationMapper;
import org.example.javawebdemo.service.ReservationService;
import org.example.javawebdemo.utils.MyBatisUtil;
import org.example.javawebdemo.utils.RoomUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ReservationServiceImpl implements ReservationService {

    private ReservationServiceImpl(){}

    @Getter
    private static ReservationService reservationService = new ReservationServiceImpl();

    @Override
    public Boolean AddReservation(String applyReason, int rid, LocalTime startHour, LocalTime endHour, int participants, LocalDate reservationDate, int user_id) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
            return mapper.addNewReservation(applyReason,rid,startHour,endHour,participants,reservationDate,user_id) > 0;
        }
    }

    @Override
    public List<Reservation> getAllReservation() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
            return mapper.getReservationList();
        }
    }

    @Override
    public List<Reservation> getReservationByUid(int user_id) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
            return  mapper.getReservationListByUid(user_id);
        }
    }

    @Override
    public Boolean approveReservation(int reservationId, String feedback_reason) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
            return  mapper.alterReservationStatus(reservationId,"approved",feedback_reason) > 0;
        }
    }

    @Override
    public Boolean rejectReservation(int reservationId, String feedback_reason) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
            return  mapper.alterReservationStatus(reservationId,"rejected",feedback_reason) > 0;
        }
    }

    @Override
    public Boolean isApprovable(int reservationId) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
            Reservation reservation = mapper.getReservationByReservationId(reservationId);
            boolean success = true;
            List<Reservation> approved = mapper.getApprovedReservationListByDayAndRoomId(reservation.getReservation_date(), reservation.getRoom_id());
            for(Reservation r : approved){
                if(RoomUtil.isOverLapped(reservation.getStart_time(),reservation.getEnd_time(),r)){
                    success = false;
                    return success;
                }
            }
            return success;
        }
    }

    @Override
    public int getApprovedReservationCount() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession(true)){
            ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
            return mapper.getApprovedReservationListOftheDay(LocalDate.now().toString()).size();
        }
    }
}
