package com.example.room.service;

import com.example.room.entity.Reservation;
import com.example.room.repository.ReservationRepository;
import com.example.room.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReserveService {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ReservationRepository reservationRepository;

    public int reserve(Reservation reservation) {
        int result = setSchedule(reservation);
        if (result > 0) {
            reservationRepository.save(reservation);
        }
        return result;
    }

    private int setSchedule(Reservation reservation) {
        List<Integer> reserveTime = DateUtil.extractList(reservation.getStartHour()
                , reservation.getStartMinute()
                , reservation.getEndHour()
                , reservation.getEndMinute());
        List<Date> recurringDays = DateUtil.getRecurringDays(reservation.getReserveDate()
                , reservation.getRecurring());
        for (Date date : recurringDays) {
            for (int timeUnit : reserveTime) {
                scheduleService.setSchedule(reservation.getRoomNumber(),
                        date, timeUnit, reservation.getUserName());
            }
        }
        return recurringDays.size() * reserveTime.size();
    }


}
