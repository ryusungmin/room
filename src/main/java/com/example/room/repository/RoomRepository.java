package com.example.room.repository;

import com.example.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Long findByRoomName(String roomName);

}
