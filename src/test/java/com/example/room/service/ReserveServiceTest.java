package com.example.room.service;

import com.example.room.entity.Reservation;
import com.example.room.entity.Room;
import com.example.room.entity.Schedule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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
    public void reserve() {
        Room room = roomService.setRoom("예약테스트회의실");


        Reservation reservation = new Reservation();
        reservation.setRoomNumber(room.getRoomNumber());
        reservation.setUserName("테스트회원");
        reservation.setReserveDate(new Date());
        reservation.setStartHour(0);
        reservation.setStartMinute(30);
        reservation.setEndHour(3);
        reservation.setEndMinute(0);
        reservation.setRecurring(3);
        reservation.setCreatedAt(new Date());

        reserveService.reserve(reservation);

        List<Schedule> allSchedule = scheduleService.getAllSchedule();
        for (Schedule schedule : allSchedule) {
            System.out.println(schedule.getReserveDate());
            System.out.println(schedule.getScheduleId());
            System.out.println(schedule.getRoomNumber());
            System.out.println(schedule.getTimeUnit());
            System.out.println();
        }

    }
}