package com.example.room.utils;

import lombok.extern.slf4j.Slf4j;

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
    public static final int DAYS_OF_WEEK = 7;

    public static List<Integer> extractList(int startHour, int startMinute, int endHour, int endMinute) {
        int startUnit;
        int endUnit;
        try {
            startUnit = parseToUnit(startHour, startMinute);
            endUnit = parseToUnit(endHour, endMinute);
            if (endUnit <= startUnit) {
                throw new Exception("startTime is later than endTime");
            }
            if (endUnit > MAX_TIME) {
                throw new Exception("endTime is not TimeUnit");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
        List<Integer> resultList = new ArrayList<>();
        for (int i = startUnit; i < endUnit; i = i + HOUR_PER_UNIT) {
            resultList.add(i);
        }
        return resultList;
    }

    private static List<Date> getRecurringDays(int yyyy, int mm, int dd, int recurring) {
        Calendar cal = Calendar.getInstance();
        cal.set(yyyy, mm, dd);
        List<Date> recurringDateList = new ArrayList<>();
        for (int i = 0; i < recurring; i++) {
            recurringDateList.add(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, DAYS_OF_WEEK);
        }
        return recurringDateList;
    }

    public static List<Date> getRecurringDays(Date date, int recurring) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getRecurringDays(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH), recurring);
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
}
