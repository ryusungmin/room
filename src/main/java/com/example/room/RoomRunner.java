package com.example.room;

import com.example.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RoomRunner implements ApplicationRunner {
    @Autowired
    private RoomService roomService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roomService.setRoom("회의실A");
        roomService.setRoom("회의실B");
        roomService.setRoom("회의실C");
        roomService.setRoom("회의실D");
        roomService.setRoom("회의실E");
    }
}
