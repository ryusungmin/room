package com.example.room.controller;

import com.example.room.model.Reserve;
import com.example.room.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReserveControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RoomService roomService;

    @Test
    public void showSchedule() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/schedule",
                String.class)).contains("예약자명");
    }

    @Test
    public void setSchedule() {
        Reserve reserve = new Reserve();
        reserve.setUserName("테스트");
        reserve.setRoomName("회의실B");
        reserve.setDate("01/25/2019");
        reserve.setStartHour("12");
        reserve.setStartMinute("00");
        reserve.setEndHour("12");
        reserve.setEndMinute("30");
        reserve.setRecurring("4");
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/setSchedule",
                reserve, String.class)).contains("회의실명을 확인해주세요");
    }
}