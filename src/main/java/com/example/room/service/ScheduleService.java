package com.example.room.service;

import com.example.room.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {

    public List<Schedule> getSchedule(Date start, Date end) {
        return new ArrayList<>();
    }

    public int addSchedule(Schedule schedule) {
        return 1;
    }

}

