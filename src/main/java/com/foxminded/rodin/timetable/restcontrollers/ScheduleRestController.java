package com.foxminded.rodin.timetable.restcontrollers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/{timetable.rest.version}")
public class ScheduleRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "/schedules")
    public List<EntityModel<Schedule>> findAllSchedules() {
        return scheduleService.findAll().stream()
                .map(schedule -> toModel(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/schedules/{id}")
    public EntityModel<Schedule> findScheduleById(@PathVariable("id") Long id) {
        return toModel(scheduleService.findById(id));
    }

    @PostMapping(value = "/schedules/{id}")
    public ResponseEntity<?> createSchedule(@RequestBody Schedule schedule) {
        EntityModel<Schedule> entityModel = toModel(scheduleService.save(schedule));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/schedules/{id}")
    public ResponseEntity<?> updateSchedule(@RequestBody Schedule schedule) {
        EntityModel<Schedule> entityModel = toModel(scheduleService.save(schedule));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/schedules/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Schedule> toModel(Schedule schedule) {
        return EntityModel.of(schedule,
                linkTo(methodOn(ScheduleRestController.class, restVersion).findScheduleById(schedule.getId())).withSelfRel(),
                linkTo(methodOn(ScheduleRestController.class, restVersion).findAllSchedules()).withRel("schedules"));
    }

    public ResponseEntity<EntityModel<Schedule>> toResponse(EntityModel<Schedule> entityModel) {
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}
