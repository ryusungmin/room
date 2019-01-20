package com.example.room.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "reservation",
        indexes = {@Index(name = "reservation_index_1", columnList = "roomNumber"),
                @Index(name = "reservation_index_2", columnList = "start"),
                @Index(name = "reservation_index_3", columnList = "end")
        })
public class Reservation {
    @Id
    @GeneratedValue
    @Column
    private Long reservationNumber;

    @Column(nullable = false)
    @NonNull
    private Long roomNumber;

    @Column(nullable = false)
    @NonNull
    private String userName;

    @Column(nullable = false)
    @NonNull
    private Date reserveDate;

    @Column(nullable = false)
    @NonNull
    private int startHour;

    @Column(nullable = false)
    @NonNull
    private int startMinute;


    @Column(nullable = false)
    @NonNull
    private int endHour;

    @Column(nullable = false)
    @NonNull
    private int endMinute;

    @Column(nullable = false)
    @NonNull
    private int recurring;

    @Column(nullable = false)
    @NonNull
    private Date createdAt;

}
