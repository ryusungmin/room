package com.example.room.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "room", uniqueConstraints = @UniqueConstraint(columnNames = "roomName"))
public class Room {

    @Id
    @GeneratedValue
    @Column
    private Long roomNumber;

    @Column(length = 20, nullable = false)
    @NonNull
    private String roomName;


}
