package com.example.room.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "schedule")
public class Schedule {

    @Column(nullable = false)
    private Long roomNumber;

    @Column(nullable = false)
    @NonNull
    private Date reserveDate;

    @Column(nullable = false)
    @NonNull
    private int timeUnit;

    @Column(nullable = false)
    @NonNull
    private String userName;


}
