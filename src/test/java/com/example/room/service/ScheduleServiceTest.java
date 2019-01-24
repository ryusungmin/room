package com.example.room.service;

import com.example.room.entity.Room;
import com.example.room.entity.Schedule;
import com.example.room.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void setSchedule_정상() throws Exception {
        Room room = roomService.setRoom("회의실테스트32");
        Schedule schedule = scheduleService.setSchedule(room.getRoomNumber(), "01/25/2053", 150, "테스트", 1);
        assertEquals(room.getRoomNumber(), schedule.getRoomNumber());
    }

    @Test
    public void setSchedule_정상2건() throws Exception {
        Room room = roomService.setRoom("회의실테스트1");
        Schedule schedule = scheduleService.setSchedule(room.getRoomNumber(), "01/25/2019", 150, "테스트", 1);
        Schedule schedule2 = scheduleService.setSchedule(room.getRoomNumber(), "01/26/2019", 150, "테스트", 1);
        assertEquals(room.getRoomNumber(), schedule.getRoomNumber());
        assertEquals(room.getRoomNumber(), schedule2.getRoomNumber());
    }

    @Test(expected = Exception.class)
    public void setSchedule_중복스케쥴체크() throws Exception {
        Room room = roomService.setRoom("회의실테스트2");
        Schedule schedule = scheduleService.setSchedule(room.getRoomNumber(), "01/25/2019", 150, "테스트", 1);
        Schedule schedule2 = scheduleService.setSchedule(room.getRoomNumber(), "01/25/2019", 150, "테스트", 1);
    }

    @Test
    public void getAllEvent_정상() throws Exception {
        Room room = roomService.setRoom("회의실테스트41");
        Schedule schedule = scheduleService.setSchedule(room.getRoomNumber(), "01/25/2019", 150, "테스트", 1);
        Schedule schedule2 = scheduleService.setSchedule(room.getRoomNumber(), "01/26/2019", 150, "테스트", 1);
        List<Event> eventList = scheduleService.getAllEvent("회의실테스트41");
        assertEquals("2019-01-25 01:30", eventList.get(0).getStart());
        assertEquals("2019-01-26 02:00", eventList.get(1).getEnd());
    }

}