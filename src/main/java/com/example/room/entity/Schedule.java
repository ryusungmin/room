package com.example.room.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "schedule")
public class Schedule {

    @Id
    @Column
    private Long roomNumber;

    @Column(nullable = false)
    @NonNull
    private Date start;

    @Column(nullable = false)
    @NonNull
    private Date end;

    @NonNull
    private String userId;


}
