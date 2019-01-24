package com.example.room.controller;

import com.example.room.model.Event;
import com.example.room.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "/getSchedule")
    public String getEvents(@RequestParam String roomName) {
        if (StringUtils.isEmpty(roomName)) {
            return null;
        }
        try {
            List<Event> events = scheduleService.getAllEvent(roomName);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
