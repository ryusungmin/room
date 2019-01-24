package com.example.room.service;

import com.example.room.entity.Room;
import com.example.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Room getRoom(String roomName) {
        return roomRepository.findByRoomName(roomName);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }


}

