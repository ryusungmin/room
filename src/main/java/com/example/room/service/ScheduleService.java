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

    public Schedule setSchedule(Long roomNumber, Date reserveDate, int timeUnit, String userName) {
        Schedule schedule = new Schedule();
        schedule.setRoomNumber(roomNumber);
        schedule.setReserveDate(reserveDate);
        schedule.setTimeUnit(timeUnit);
        schedule.setUserName(userName);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduleListByDay(Date date) {
        Calendar calendar = getCalenderDay(date);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = calendar.getTime();
        return scheduleRepository.findByReserveDateBetween(startDate, endDate);
    }

    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    private Calendar getCalenderDay(Date date) {
        Calendar myCal = new GregorianCalendar();
        myCal.setTime(date);
        int year = myCal.get(Calendar.YEAR);
        int month = myCal.get(Calendar.MONTH);
        int day = myCal.get(Calendar.DAY_OF_MONTH);
        return new GregorianCalendar(year, month, day);
    }
}

