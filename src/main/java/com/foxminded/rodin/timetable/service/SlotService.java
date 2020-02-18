package com.foxminded.rodin.timetable.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.model.schedules.Slot;
import com.foxminded.rodin.timetable.repo.SlotRepository;

@Service
public class SlotService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a slot by id=%d";

    private static final Logger logger = LoggerFactory.getLogger(SlotService.class);

    @Autowired
    private SlotRepository slotRepository;

    public List<Slot> findAll() {
        List<Slot> slots = (List<Slot>) slotRepository.findAll();
        return slots;
    }

    public Slot save(Slot slot) {
        return slotRepository.save(slot);
    }

    public List<Slot> saveAll(List<Slot> slots) {
        return (List<Slot>) slotRepository.saveAll(slots);
    }

    public Slot findById(long id) {
        return slotRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            logger.error(errorMessage);
            return new ElementNotFoundException(errorMessage);
        });
    }

    public List<Room> findAvailableRooms(long id, @NonNull LocalDateTime startDate, @NonNull LocalDateTime endDate) {
        return slotRepository.findAvailableRooms(id, startDate, endDate);
    }

    public List<Group> findAvailableGroups(long id, @NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime) {
        return slotRepository.findAvailableGroups(id, startTime, endTime);
    }

    public List<Teacher> findAvailableTeachers(long id, @NonNull LocalDateTime startTime,
            @NonNull LocalDateTime endTime) {
        return slotRepository.findAvailableTeachers(id, startTime, endTime);
    }

}
