package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.model.curriculums.Subject;
import com.foxminded.rodin.timetable.repo.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	public List<Subject> findAll() {
		List<Subject> subjects = (List<Subject>) subjectRepository.findAll();
		return subjects;
	}

}
