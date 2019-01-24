package com.example.room.model;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class FullCalendarDate {
    private String fullDate; // format: "MM/dd/yyyy"
    private int year;
    private int month;
    private int day;
    private Date date;


    public FullCalendarDate(String fullDate) {
        try {
            if (fullDate == null || StringUtils.isEmpty(fullDate)) {
                throw new IllegalArgumentException("Invalid fullDate");
            }
            String[] split = fullDate.split("/");
            int year = Integer.parseInt(split[2]);
            int month = Integer.parseInt(split[0]);
            int day = Integer.parseInt(split[1]);
            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, day);

            this.fullDate = fullDate;
            this.year = year;
            this.month = month;
            this.day = day;
            this.date = cal.getTime();
        } catch (Exception e) {

            this.fullDate = "";
            this.year = 0;
            this.month = 0;
            this.day = 0;
            date = null;
        }
    }

    public FullCalendarDate(Date date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            this.year = calendar.get(Calendar.YEAR);
            this.month = calendar.get(Calendar.MONTH) + 1;
            this.day = calendar.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            this.fullDate = simpleDateFormat.format(calendar.getTime());
            this.date = calendar.getTime();
        } catch (Exception e) {
            this.fullDate = "";
            this.year = 0;
            this.month = 0;
            this.day = 0;
            date = null;
        }
    }


}
