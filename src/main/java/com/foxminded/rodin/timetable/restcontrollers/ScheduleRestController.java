package com.foxminded.rodin.timetable.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.rodin.timetable.model.schedules.Schedule;
import com.foxminded.rodin.timetable.service.ScheduleService;

@RestController
@RequestMapping("/api/${timetable.rest.version}")
public class ScheduleRestController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "/schedules")
    public List<Schedule> findAllSchedules() {
        return scheduleService.findAll();
    }

    @GetMapping(value = "/schedules/{id}")
    public Schedule findScheduleById(@PathVariable("id") Long id) {
        return scheduleService.findById(id);
    }

    @PostMapping(value = "/schedules/{id}")
    public void createSchedule(@RequestBody Schedule schedule) {
        scheduleService.save(schedule);
    }

    @PutMapping(value = "/schedules/{id}")
    public void updateSchedule(@RequestBody Schedule schedule) {
        scheduleService.save(schedule);
    }

    @DeleteMapping(value = "/schedules/{id}")
    public void deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteById(id);
    }

}
