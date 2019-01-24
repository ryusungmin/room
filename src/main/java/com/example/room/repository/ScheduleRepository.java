package com.example.room.repository;

import com.example.room.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByRoomNumber(Long roomNumber);

    Schedule findOneByRoomNumberAndReserveDateAndTimeUnit(Long roomNumber, String reserveDate, int timeUnit);
}
