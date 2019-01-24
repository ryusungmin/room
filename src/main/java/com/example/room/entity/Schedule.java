package com.example.room.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "schedule", uniqueConstraints = @UniqueConstraint(columnNames = "scheduleKey"))
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
    private String reserveDate;


    @Column(nullable = false)
    @NonNull
    private int timeUnit;

    @Column(nullable = false)
    @NonNull
    private int recurring;

    @Column(nullable = false)
    @NonNull
    private String userName;

    @Column(nullable = false)
    @NonNull
    private String scheduleKey;




}
