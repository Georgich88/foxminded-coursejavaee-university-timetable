package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.facilities.Building;

public interface BuildingRepository extends CrudRepository<Building, Long> {

}
