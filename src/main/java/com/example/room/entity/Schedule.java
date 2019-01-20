package com.example.room.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "schedule",
        uniqueConstraints = @UniqueConstraint(columnNames = {"roomNumber", "reserveDate", "timeUnit"}))
public class Schedule {

    @Id
    @GeneratedValue
    @Column
    private Long scheduleId;

    @Column(nullable = false)
    @NonNull
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
