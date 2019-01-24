package com.example.room.utils;

import com.example.room.model.FullCalendarDate;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public class DateUtil {
    public static final int TIME_UNIT = 30;
    public static final int HOUR_PER_UNIT = 50;
    public static final int MAX_TIME = 2400;
    public static final int O_CLOCK = 0;
    public static final int HOUR = 100;

    public static List<Integer> extractList(int startHour, int startMinute, int endHour, int endMinute) throws Exception {
        int startUnit;
        int endUnit;

        startUnit = parseToUnit(startHour, startMinute);
        endUnit = parseToUnit(endHour, endMinute);
        if (endUnit <= startUnit) {
            throw new Exception("시작시간과 종료시간이 같거나, 종료시간이 시작시간보다 먼저입니다.");
        }
        if (endUnit > MAX_TIME) {
            throw new Exception("종료시간이 24시를 넘어갑니다.");
        }

        List<Integer> resultList = new ArrayList<>();
        for (int i = startUnit; i < endUnit; i = i + HOUR_PER_UNIT) {
            resultList.add(i);
        }
        return resultList;
    }

    private static List<String> getRecurringDays(int yyyy, int mm, int dd, int recurring) {
        Calendar cal = Calendar.getInstance();
        cal.set(yyyy, mm - 1, dd, 0, 0, 0);
        List<String> recurringDateList = new ArrayList<>();
        for (int i = 0; i < recurring; i++) {
            FullCalendarDate fullCalendarDate = new FullCalendarDate(cal.getTime());
            recurringDateList.add(fullCalendarDate.getFullDate());
            cal.add(Calendar.DAY_OF_MONTH, 7);
        }
        return recurringDateList;
    }

    public static List<String> getRecurringDays(String date, int recurring) {
        FullCalendarDate fullCalendarDate = new FullCalendarDate(date);
        if (fullCalendarDate == null || fullCalendarDate.getDate() == null) {
            throw new IllegalArgumentException("Invalid date");
        }
        return getRecurringDays(fullCalendarDate.getYear(),
                fullCalendarDate.getMonth(),
                fullCalendarDate.getDay(), recurring);
    }


    private static int parseToUnit(int hour, int minute) throws Exception {
        if (hour < 0) {
            throw new Exception("hour are invalid");
        }
        if (minute == O_CLOCK) {
            return hour * HOUR;
        }
        if (minute == TIME_UNIT) {
            return hour * HOUR + HOUR_PER_UNIT;
        }
        throw new Exception("minute is not TIME_UNIT TYPE");
    }


    public static String getStartTime(String reserveDate, int timeUnit) {
        FullCalendarDate fullCalendarDate = new FullCalendarDate(reserveDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fullCalendarDate.getDate());
        calendar.set(Calendar.HOUR_OF_DAY, timeUnit / HOUR);
        calendar.set(Calendar.MINUTE, timeUnit % HOUR == HOUR_PER_UNIT ? TIME_UNIT : 0);
        Date date = calendar.getTime();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return transFormat.format(date);
    }

    public static String getEndTime(String reserveDate, int timeUnit) {
        FullCalendarDate fullCalendarDate = new FullCalendarDate(reserveDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fullCalendarDate.getDate());
        calendar.set(Calendar.HOUR_OF_DAY, timeUnit / HOUR);
        calendar.set(Calendar.MINUTE, timeUnit % HOUR + HOUR_PER_UNIT == HOUR_PER_UNIT ? TIME_UNIT : 60);
        Date date = calendar.getTime();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return transFormat.format(date);
    }
}
