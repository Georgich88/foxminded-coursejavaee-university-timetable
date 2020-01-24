package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.model.facilities.Building;
import com.foxminded.rodin.timetable.repo.BuildingRepository;

@Service
public class BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	public List<Building> findAll() {
		List<Building> universities = (List<Building>) buildingRepository.findAll();
		return universities;
	}

}
