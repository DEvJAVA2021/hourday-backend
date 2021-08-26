package com.devjava.hourday.service;

import com.devjava.hourday.common.advice.exception.schedule.ScheduleNotFoundException;
import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule getScheduleList(User user, LocalDate date) {
        return scheduleRepository.findByWriterAndWriteDate(user, date).orElseThrow(ScheduleNotFoundException::new);
    }

}
