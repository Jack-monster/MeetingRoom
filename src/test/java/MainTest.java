import org.apache.ibatis.session.SqlSession;
import org.example.javawebdemo.entity.MeetingRoom;
import org.example.javawebdemo.entity.Reservation;
import org.example.javawebdemo.entity.User;
import org.example.javawebdemo.mapper.MeetingRoomMapper;
import org.example.javawebdemo.mapper.ReservationMapper;
import org.example.javawebdemo.mapper.UserMapper;
import org.example.javawebdemo.service.impl.RoomServiceImpl;
import org.example.javawebdemo.utils.MyBatisUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class MainTest {
    @Test
    public void test1(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);

    }

    @Test
    public void test2(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User mapperUser = mapper.getUser("admin", "123456");
        System.out.println(mapperUser.toString());
    }

    @Test
    public void test3(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        MeetingRoomMapper mapper = sqlSession.getMapper(MeetingRoomMapper.class);
        List<MeetingRoom> roomList = mapper.getRoomList();
        System.out.println(roomList.toString());
        List<MeetingRoom> reservedRoomList = mapper.getReservedRoomList();
        System.out.println(reservedRoomList.toString());
        System.out.println(RoomServiceImpl.getRoomService().getRoomMap().toString());
    }

    @Test
    public void test4(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
        List<Reservation> reservationList = mapper.getReservationList();
        System.out.println(reservationList.toString());
        List<Reservation> approvedReservationListOftheDay = mapper.getApprovedReservationListOftheDay("2024-12-11");
        System.out.println(approvedReservationListOftheDay.toString());
    }

    @Test
    public void test5(){
        System.out.println(LocalDate.now().toString());
    }
}
