package com.example.room.service;

import com.example.room.entity.Reservation;
import com.example.room.entity.Room;
import com.example.room.model.FullCalendarDate;
import com.example.room.model.Reserve;
import com.example.room.repository.ReservationRepository;
import com.example.room.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ReserveService {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationRepository reservationRepository;

    private boolean reserve(Reservation reservation) throws Exception {
        int result = setSchedule(reservation);
        if (result == 0) {
            throw new Exception("failed save schedule");
        }
        reservationRepository.save(reservation);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean reserve(Reserve reserve) throws Exception {
        Room room = roomService.getRoom(reserve.getRoomName());
        if (room == null) {
            throw new IllegalArgumentException("회의실명을 확인해주세요");
        }

        String date = reserve.getDate();
        FullCalendarDate fullCalendarDate = new FullCalendarDate(date);
        if (fullCalendarDate.getDate() == null) {
            throw new IllegalArgumentException("날짜를 확인해주세요");
        }

        int startHourInt = Integer.parseInt(reserve.getStartHour());
        int startMinuteInt = Integer.parseInt(reserve.getStartMinute());
        int endHourInt = Integer.parseInt(reserve.getEndHour());
        int endMinuteInt = Integer.parseInt(reserve.getEndMinute());

        String userName = reserve.getUserName();
        if (StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("예약자명을 확인해주세요");
        }

        int recurring = Integer.parseInt(reserve.getRecurring());
        if (recurring < 1) {
            throw new IllegalArgumentException("반복횟수를 확인해주세요");
        }

        Reservation reservation = new Reservation();
        reservation.setRoomNumber(room.getRoomNumber());
        reservation.setReserveDate(date);
        reservation.setUserName(userName);
        reservation.setStartHour(startHourInt);
        reservation.setStartMinute(startMinuteInt);
        reservation.setEndHour(endHourInt);
        reservation.setEndMinute(endMinuteInt);
        reservation.setRecurring(recurring);
        reservation.setCreatedAt(new Date());

        reserve(reservation);
        return true;

    }


    private int setSchedule(Reservation reservation) throws Exception {
        List<Integer> reserveTime = DateUtil.extractList(reservation.getStartHour()
                , reservation.getStartMinute()
                , reservation.getEndHour()
                , reservation.getEndMinute());
        List<String> recurringDays = DateUtil.getRecurringDays(reservation.getReserveDate()
                , reservation.getRecurring());

        for (String date : recurringDays) {
            for (int timeUnit : reserveTime) {
                scheduleService.setSchedule(reservation.getRoomNumber(),
                        date, timeUnit, reservation.getUserName(), reservation.getRecurring());
            }
        }
        return recurringDays.size() * reserveTime.size();
    }


}
