package com.example.room.model;

import lombok.Data;

@Data
public class Reserve {
    private String roomName;
    private String date;
    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;
    private String userName;
    private String recurring;
}
