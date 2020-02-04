package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.facilities.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

}
