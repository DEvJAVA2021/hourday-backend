package com.devjava.hourday.repository;

import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByWriterAndWriteDate(User user, LocalDate date);
}
