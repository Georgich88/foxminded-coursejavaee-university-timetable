package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.schedules.Schedule;
import com.foxminded.rodin.timetable.repo.ScheduleRepository;

@Service
public class ScheduleService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a schedule by id=%d";

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> findAll() {
        List<Schedule> schedules = (List<Schedule>) scheduleRepository.findAll();
        return schedules;
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule findById(long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

}
