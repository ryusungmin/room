package com.example.room.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    @Column
    private Long roomNumber;

    @Column(length = 20, nullable = false)
    @NonNull
    private String roomName;


}
