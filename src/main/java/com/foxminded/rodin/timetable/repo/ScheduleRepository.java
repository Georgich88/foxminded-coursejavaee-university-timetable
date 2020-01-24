package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.schedules.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

}
