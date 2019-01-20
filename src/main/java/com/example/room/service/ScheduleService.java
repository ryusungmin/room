package com.example.room.service;

import com.example.room.entity.Schedule;
import com.example.room.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public int setSchedule(Long roomNumber, Date reserveDate, int timeUnit, String userName) {
        Schedule schedule = new Schedule(roomNumber, reserveDate, timeUnit, userName);
        scheduleRepository.save(schedule);
        return 1;
    }

    public List<Schedule> getScheduleListByDay(Date date) {
        Calendar myCal = new GregorianCalendar();
        myCal.setTime(date);
        return new ArrayList<>();
    }



}

