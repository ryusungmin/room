package com.example.room.service;

import com.example.room.entity.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    public void setRoom_정상() {
        Room room = roomService.setRoom("회의실테스트");
        assertEquals("회의실테스트", room.getRoomName());
    }

    @Test(expected = Exception.class)
    public void setRoom_중복() {
        Room room = roomService.setRoom("회의실테스트");
        Room room2 = roomService.setRoom("회의실테스트");
    }


    @Test
    public void getAllRoom_정상() {
        Room setRoom3 = roomService.setRoom("회의실테스트3");
        Room setRoom4 = roomService.setRoom("회의실테스트4");
        List<Room> roomList = roomService.getAllRooms();
        boolean flag1 = false;
        boolean flag2 = false;
        for (Room room : roomList) {
            if (room.getRoomNumber() == setRoom3.getRoomNumber()) {
                flag1 = true;
            }
            if (room.getRoomNumber() == setRoom4.getRoomNumber()) {
                flag2 = true;
            }
        }
        assertTrue(flag1);
        assertTrue(flag2);
    }



}