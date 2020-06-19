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

import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.service.CourseService;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class CourseRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    public List<EntityModel<Course>> findAllCourses() {
        return courseService.findAll().stream()
                .map(course -> toModel(course))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/courses/{id}")
    public EntityModel<Course> findCourseById(@PathVariable("id") Long id) {
        return toModel(courseService.findById(id));
    }

    @PostMapping(value = "/courses/{id}")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        EntityModel<Course> entityModel = toModel(courseService.save(course));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/courses/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody Course course) {
        EntityModel<Course> entityModel = toModel(courseService.save(course));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Course> toModel(Course course) {
        return EntityModel.of(course,
                linkTo(methodOn(CourseRestController.class, restVersion).findCourseById(course.getId())).withSelfRel(),
                linkTo(methodOn(CourseRestController.class, restVersion).findAllCourses()).withRel("courses"));
    }

    public ResponseEntity<EntityModel<Course>> toResponse(EntityModel<Course> entityModel) {
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}
