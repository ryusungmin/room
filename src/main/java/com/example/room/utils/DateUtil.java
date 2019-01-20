package com.example.room.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    public static final int TIME_UNIT = 30;
    public static final int HOUR_PER_UNIT = 50;
    public static final int MAX_TIME = 2400;
    public static final int O_CLOCK = 0;
    public static final int HOUR = 100;

    public static List<Integer> extractList(int startHour, int startMinute, int endHour, int endMinute) throws Exception {
        int startUnit = parseToUnit(startHour, startMinute);
        int endUnit = parseToUnit(endHour, endMinute);
        if (endUnit <= startUnit) {
            throw new Exception("startTime is later than endTime");
        }
        if (endUnit > MAX_TIME) {
            throw new Exception("endTime is not TimeUnit");
        }
        List<Integer> resultList = new ArrayList<>();
        for (int i = startUnit; i < endUnit; i = i + HOUR_PER_UNIT) {
            resultList.add(i);
        }
        return resultList;
    }

    public static List<Date> getRecurringDays(int yyyy, int mm, int dd, int recurring, int recurringUnit) {
        Calendar cal = Calendar.getInstance();
        cal.set(yyyy, mm - 1, dd);
        List<Date> recurringDateList = new ArrayList<>();
        for (int i = 0; i < recurring; i++) {
            recurringDateList.add(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, recurringUnit);
        }
        return recurringDateList;
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
