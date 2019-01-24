package com.example.room.service;

import com.example.room.entity.Room;
import com.example.room.entity.Schedule;
import com.example.room.model.Event;
import com.example.room.repository.ScheduleRepository;
import com.example.room.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RoomService roomService;

    public Schedule setSchedule(Long roomNumber, String reserveDate, int timeUnit, String userName, int recurring) throws Exception {
        Schedule duplicated =
                scheduleRepository.findOneByRoomNumberAndReserveDateAndTimeUnit(roomNumber, reserveDate, timeUnit);
        if (duplicated != null) {
            throw new Exception("이미 예약된 시간입니다.");
        }

        Schedule schedule = new Schedule();
        schedule.setRoomNumber(roomNumber);
        schedule.setReserveDate(reserveDate);
        schedule.setTimeUnit(timeUnit);
        schedule.setUserName(userName);
        schedule.setRecurring(recurring);
        schedule.setScheduleKey(roomNumber + reserveDate + timeUnit);
        return scheduleRepository.save(schedule);
    }


    public List<Event> getAllEvent(String roomName) {
        Room room = roomService.getRoom(roomName);
        if (room == null) {
            return new ArrayList<>();
        }
        List<Schedule> scheduleList = scheduleRepository.findAllByRoomNumber(room.getRoomNumber());
        List<Event> eventList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            eventList.add(convert(schedule));
        }
        return eventList;
    }

    private Event convert(Schedule schedule) {
        Event result = new Event();
        String userName = schedule.getUserName();
        result.setTitle("[" + userName + "]");
        result.setStart(DateUtil.getStartTime(schedule.getReserveDate(), schedule.getTimeUnit()));
        result.setEnd(DateUtil.getEndTime(schedule.getReserveDate(), schedule.getTimeUnit()));
        return result;
    }


}

