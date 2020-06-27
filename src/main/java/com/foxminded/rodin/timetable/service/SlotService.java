package com.foxminded.rodin.timetable.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.model.schedules.Slot;
import com.foxminded.rodin.timetable.repo.SlotRepository;

@Service
public class SlotService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a slot by id=%d";

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

    public List<Slot> findSlotsByStudentId(long id) {
        return slotRepository.findSlotsByStudentId(id);
    }

    @Transactional
    public void deleteById(Long id) {
        Slot slot = slotRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
        slotRepository.deleteScheduleSlotsBySlotId(id);
        slotRepository.delete(slot);

    }

}
