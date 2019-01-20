package com.example.room.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reservation",
        indexes = {@Index(name = "reservation_index_1", columnList = "roomNumber"),
                @Index(name = "reservation_index_2", columnList = "reserveDate")
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
