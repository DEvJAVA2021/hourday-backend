package com.devjava.hourday.service;

import com.devjava.hourday.common.advice.exception.schedule.ScheduleNotFoundException;
import com.devjava.hourday.common.advice.exception.user.UserAuthenticationException;
import com.devjava.hourday.entity.Category;
import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.repository.CategoryRepository;
import com.devjava.hourday.repository.DetailScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@RequiredArgsConstructor
@Service
public class DetailSchedulerService {

    private final DetailScheduleRepository detailScheduleRepository;
    private final CategoryRepository categoryRepository;

    public void saveDetailSchedule(DetailSchedule detailSchedule, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(ScheduleNotFoundException::new);
        detailSchedule.setCategory(category);
        detailScheduleRepository.save(detailSchedule);
    }

    public void updateEndTime(DetailSchedule detailSchedule) {
        detailSchedule.setEndTime(LocalTime.now());
        detailScheduleRepository.save(detailSchedule);
    }

    public DetailSchedule checkValid(Long detailScheduleId, User user) {
        DetailSchedule detailSchedule = detailScheduleRepository.findById(detailScheduleId).orElseThrow(ScheduleNotFoundException::new);
        if (!detailSchedule.getSchedule().getWriter().getId().equals(user.getId())) {
            throw new UserAuthenticationException();
        }
        return detailSchedule;
    }

}
