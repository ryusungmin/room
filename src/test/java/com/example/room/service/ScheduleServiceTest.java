package com.example.room.service;

import com.example.room.entity.Room;
import com.example.room.entity.Schedule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RoomService roomService;

    @Test
    public void setSchedule() {
        Room room = roomService.setRoom("회의실테스트");
        Schedule schedule = scheduleService.setSchedule(room.getRoomNumber(), new Date(), 130, "테스트");
        assertEquals("회의실테스트", room.getRoomName());
    }

    @Test
    public void getScheduleListByDay() {
        Room room = roomService.setRoom("회의실테스트1");
        scheduleService.setSchedule(room.getRoomNumber(), new Date(), 130, "테스트");

        Room room2 = roomService.setRoom("회의실테스트2");
        scheduleService.setSchedule(room2.getRoomNumber(), new Date(), 200, "테스트");

        List<Schedule> scheduleListByDay = scheduleService.getScheduleListByDay(new Date());
        for (Schedule schedule1 : scheduleListByDay) {
            System.out.println(schedule1.getReserveDate());
            System.out.println(schedule1.getScheduleId());
            System.out.println(schedule1.getRoomNumber());
            System.out.println(schedule1.getTimeUnit());
        }
    }
}