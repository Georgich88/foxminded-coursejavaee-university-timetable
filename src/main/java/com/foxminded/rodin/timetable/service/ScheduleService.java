package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.schedules.Schedule;
import com.foxminded.rodin.timetable.repo.ScheduleRepository;
import com.foxminded.rodin.timetable.repo.SlotRepository;

@Service
public class ScheduleService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a schedule by id=%d";

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private SlotRepository slotRepository;

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

    @Transactional
    public void deleteById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
        slotRepository.deleteAll(schedule.getSlots());
        schedule.getSlots().clear();
        scheduleRepository.delete(schedule);
    }

}
