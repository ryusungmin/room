package com.example.room.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name= "member")
public class Member {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(length = 20, nullable = false)
    @NonNull
    private String username;


}
