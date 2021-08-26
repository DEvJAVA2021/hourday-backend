package com.devjava.hourday.repository;

import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByWriterAndWriteDate(User user, LocalDate date);

    @Query(value = "SELECT s FROM Schedule s WHERE s.writer.isPublic = true OR EXISTS (SELECT f FROM Follow f WHERE f.following = :user AND f.follower = s.writer)")
    List<Schedule> getAllScheduleList(User user);

}
