package com.example.room.service;

import com.example.room.entity.Schedule;
import com.example.room.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
        int year = myCal.get(Calendar.YEAR);
        int month = myCal.get(Calendar.MONTH);
        int day = myCal.get(Calendar.DAY_OF_MONTH);
        Calendar calendar = new GregorianCalendar(year, month, day);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();
        return scheduleRepository.findAllReserveDateBetween(startDate, endDate);
    }
}

