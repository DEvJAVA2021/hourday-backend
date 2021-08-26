package com.devjava.hourday.service;

import com.devjava.hourday.common.advice.exception.schedule.ScheduleNotFoundException;
import com.devjava.hourday.entity.Category;
import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.repository.CategoryRepository;
import com.devjava.hourday.repository.DetailScheduleRepository;
import com.devjava.hourday.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DetailSchedulerService {

    private final DetailScheduleRepository detailScheduleRepository;
    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;


    public List<DetailSchedule> getDetailScheduleList(Schedule schedule) {
        return detailScheduleRepository.findAllBySchedule(schedule);
    }

    public void saveDetailSchedule(DetailSchedule detailSchedule, Long scheduleId, Long categoryId) {
        // 스케줄 설정
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
        detailSchedule.setSchedule(schedule);
        // 카테고리 설정
        Category category = categoryRepository.findById(categoryId).orElseThrow(ScheduleNotFoundException::new);
        detailSchedule.setCategory(category);
        // detailSchedule 저장
        detailScheduleRepository.save(detailSchedule);
    }

}
