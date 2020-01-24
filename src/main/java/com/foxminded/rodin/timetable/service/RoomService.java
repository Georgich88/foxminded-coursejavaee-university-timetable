package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.repo.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public List<Room> findAll() {
		List<Room> rooms = (List<Room>) roomRepository.findAll();
		return rooms;
	}

}
