package com.example.room.service;

import com.example.room.model.Event;
import com.example.room.model.Reserve;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReserveServiceTest {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void reserve_정상() throws Exception {
        Reserve reserve = new Reserve();
        reserve.setUserName("테스트");
        reserve.setRoomName("회의실A");
        reserve.setDate("01/26/2019");
        reserve.setStartHour("12");
        reserve.setStartMinute("00");
        reserve.setEndHour("12");
        reserve.setEndMinute("30");
        reserve.setRecurring("1");

        reserveService.reserve(reserve);

        List<Event> allEvent = scheduleService.getAllEvent("회의실A");
        boolean flag = false;
        for (Event event : allEvent) {
            if ("2019-01-26 12:00".equalsIgnoreCase(event.getStart())) {
                flag = true;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void reserve_정상반복() throws Exception {
        Reserve reserve = new Reserve();
        reserve.setUserName("테스트");
        reserve.setRoomName("회의실A");
        reserve.setDate("01/26/2020");
        reserve.setStartHour("12");
        reserve.setStartMinute("00");
        reserve.setEndHour("12");
        reserve.setEndMinute("30");
        reserve.setRecurring("2");

        reserveService.reserve(reserve);

        List<Event> allEvent = scheduleService.getAllEvent("회의실A");
        boolean flag = false;
        boolean flag2 = false;
        for (Event event : allEvent) {
            if ("2019-01-26 12:00".equalsIgnoreCase(event.getStart())) {
                flag = true;
            }
            if ("2019-02-02 12:00".equalsIgnoreCase(event.getStart())) {
                flag2 = true;
            }
        }
        assertTrue(flag);
    }

    @Test(expected = Exception.class)
    public void reserve_중복시간() throws Exception {
        Reserve reserve = new Reserve();
        reserve.setUserName("테스트");
        reserve.setRoomName("회의실A");
        reserve.setDate("01/26/2021");
        reserve.setStartHour("12");
        reserve.setStartMinute("00");
        reserve.setEndHour("12");
        reserve.setEndMinute("30");
        reserve.setRecurring("1");

        Reserve reserve1 = new Reserve();
        reserve1.setUserName("테스트");
        reserve1.setRoomName("회의실A");
        reserve1.setDate("01/26/2021");
        reserve1.setStartHour("12");
        reserve1.setStartMinute("00");
        reserve1.setEndHour("12");
        reserve1.setEndMinute("30");
        reserve1.setRecurring("1");

        boolean flag1 = reserveService.reserve(reserve);
        boolean flag2 = reserveService.reserve(reserve1);


    }

    @Test(expected = Exception.class)
    public void reserve_시작시간종료시간같음() throws Exception {
        Reserve reserve = new Reserve();
        reserve.setUserName("테스트");
        reserve.setRoomName("회의실A");
        reserve.setDate("01/26/2030");
        reserve.setStartHour("12");
        reserve.setStartMinute("00");
        reserve.setEndHour("12");
        reserve.setEndMinute("00");
        reserve.setRecurring("1");

        boolean flag1 = reserveService.reserve(reserve);

    }

    @Test(expected = Exception.class)
    public void reserve_종료시간이앞서는경우1() throws Exception {
        Reserve reserve = new Reserve();
        reserve.setUserName("테스트");
        reserve.setRoomName("회의실A");
        reserve.setDate("01/26/2030");
        reserve.setStartHour("12");
        reserve.setStartMinute("30");
        reserve.setEndHour("12");
        reserve.setEndMinute("00");
        reserve.setRecurring("1");

        boolean flag1 = reserveService.reserve(reserve);

    }

    @Test(expected = Exception.class)
    public void reserve_종료시간이앞서는경우2() throws Exception {
        Reserve reserve = new Reserve();
        reserve.setUserName("테스트");
        reserve.setRoomName("회의실A");
        reserve.setDate("01/26/2030");
        reserve.setStartHour("15");
        reserve.setStartMinute("00");
        reserve.setEndHour("12");
        reserve.setEndMinute("00");
        reserve.setRecurring("1");

        boolean flag1 = reserveService.reserve(reserve);

    }

    @Test(expected = Exception.class)
    public void reserve_명시한시간이아닌반복이중복되는경우() throws Exception {
        Reserve reserve = new Reserve();
        reserve.setUserName("테스트");
        reserve.setRoomName("회의실B");
        reserve.setDate("01/25/2019");
        reserve.setStartHour("12");
        reserve.setStartMinute("00");
        reserve.setEndHour("12");
        reserve.setEndMinute("30");
        reserve.setRecurring("4");

        Reserve reserve1 = new Reserve();
        reserve1.setUserName("테스트");
        reserve1.setRoomName("회의실B");
        reserve1.setDate("01/11/2019");
        reserve1.setStartHour("12");
        reserve1.setStartMinute("00");
        reserve1.setEndHour("15");
        reserve1.setEndMinute("30");
        reserve1.setRecurring("3");

        boolean flag1 = reserveService.reserve(reserve);
        boolean flag2 = reserveService.reserve(reserve1);


    }
}