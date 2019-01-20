package com.example.room.service;

import com.example.room.entity.Room;
import com.example.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public Room setRoom(String roomName) {
        Room room = new Room();
        room.setRoomName(roomName);
        Room insertedRoom = roomRepository.save(room);
        return insertedRoom;
    }

    public Long getRoomNumber(String roomName) {
        Room room = roomRepository.findByRoomName(roomName);
        return room.getRoomNumber();
    }

    public String getRoomName(Long roomNumber) {
        Optional<Room> byId = roomRepository.findById(roomNumber);
        return byId.get().getRoomName(); // TODO null 처리
    }
}

