package com.example.room.controller;

import com.example.room.entity.Room;
import com.example.room.service.ReserveService;
import com.example.room.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ReserveController.class)
public class ReserveControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReserveController reserveController;

    @Mock
    private Model model;

    @Mock
    private RoomService roomService;

    @Mock
    private ReserveService reserveService;

    @Mock
    private String roomName;

    @Mock
    private Room room;

    @Mock
    private List<Room> rooms;

    @Test
    public void showSchedule() {


    }

    @Test
    public void setSchedule() {
    }
}