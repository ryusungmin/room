package com.example.room.service;

import com.example.room.entity.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    public void setRoom() {
        Room room = roomService.setRoom("회의실테스트");
        assertEquals("회의실테스트", room.getRoomName());
    }

    @Test
    public void getRoomNumber() {
        Room room = roomService.setRoom("회의실테스트2");
        Long roomNumber = roomService.getRoomNumber("회의실테스트2");
    }

    @Test
    public void getRoomName() {
        Room room = roomService.setRoom("회의실테스트3");
        Long roomNumber = roomService.getRoomNumber("회의실테스트3");
        String roomName = roomService.getRoomName(roomNumber);
        assertEquals("회의실테스트3", roomName);
    }
}