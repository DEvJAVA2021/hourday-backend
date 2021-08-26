package com.devjava.hourday.repository;

import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailScheduleRepository extends JpaRepository<DetailSchedule, Long> {

    List<DetailSchedule> findAllBySchedule(Schedule schedule);

}
