package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.schedules.Schedule;
import com.foxminded.rodin.timetable.repo.ScheduleRepository;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> findAll() {
        List<Schedule> schedules = (List<Schedule>) scheduleRepository.findAll();
        return schedules;
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule findById(@NonNull Long id) {
        return scheduleRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

}
