package com.foxminded.rodin.timetable.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.model.schedules.Slot;
import com.foxminded.rodin.timetable.repo.SlotRepository;

@Service
public class SlotService {

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

	public Slot findById(Long id) {
		return slotRepository.findById(id).get();
	}

	public List<Room> findAvailableRooms(Long id, LocalDateTime startDate, LocalDateTime endDate) {
		return slotRepository.findAvailableRooms(id, startDate, endDate);
	}

	public List<Group> findAvailableGroups(Long id, LocalDateTime startTime, LocalDateTime endTime) {
		return slotRepository.findAvailableGroups(id, startTime, endTime);
	}

	public List<Teacher> findAvailableTeachers(Long id, LocalDateTime startTime, LocalDateTime endTime) {
		return slotRepository.findAvailableTeachers(id, startTime, endTime);
	}

}
