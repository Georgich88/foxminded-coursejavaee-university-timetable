package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.schedules.Schedule;
import com.foxminded.rodin.timetable.repo.ScheduleRepository;

@Service
public class ScheduleService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a schedule by id={}";

    private static final Logger logger = LoggerFactory.getLogger(CourseSectionService.class);

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
            logger.error(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException();
        });
    }

}
