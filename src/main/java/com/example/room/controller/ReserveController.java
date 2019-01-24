package com.example.room.controller;

import com.example.room.entity.Room;
import com.example.room.model.Reserve;
import com.example.room.service.ReserveService;
import com.example.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReserveController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private ReserveService reserveService;

    @RequestMapping(value = "/schedule")
    public String showSchedule(Model model, @RequestParam(value = "roomName", required = false) String roomName) {

        if (roomName == null || StringUtils.isEmpty(roomName)) {
            roomName = null;
        } else {
            Room room = roomService.getRoom(roomName);
            if (room == null) {
                roomName = null;
            }
        }
        List<Room> roomList = roomService.getAllRooms();

        model.addAttribute("reserveRoom", roomName);
        model.addAttribute("roomList", roomList);
        return "schedule";
    }

    @RequestMapping(value = "/setSchedule", method = RequestMethod.POST)
    @ResponseBody
    public String setSchedule(@ModelAttribute Reserve reserve) {
        try {
            if (reserveService.reserve(reserve)) {
                return "예약성공";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "예약실패";
    }


}
