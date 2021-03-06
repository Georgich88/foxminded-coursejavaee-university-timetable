package com.foxminded.rodin.timetable.model.curriculums;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "courses")
public class Course {

    private static final String ERROR_COURSE_NAME_IS_MANDATORY_MESSAGE = "Course name is mandatory";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = ERROR_COURSE_NAME_IS_MANDATORY_MESSAGE)
    private String name;
    private String description;
    @Column(name = "courses_sections")
    @OneToMany
    private List<CourseSection> sections;

    public Course() {
        this.name = "";
        this.description = "";
        this.sections = new ArrayList<CourseSection>();
    }

    public Course(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sections = new ArrayList<CourseSection>();
    }

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
        this.sections = new ArrayList<CourseSection>();
    }

    public CourseSection addNewSection(String name, String notes, String gradingBasis, int timeRequired) {
        CourseSection section = new CourseSection(name, notes, gradingBasis, timeRequired);
        this.sections.add(section);
        return section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CourseSection> getSections() {
        return sections;
    }

    public void setSections(List<CourseSection> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Course [id=").append(id).append(", name=").append(name).append(", description=")
                .append(description).append(", sections=").append(sections).append("]");
        return builder.toString();
    }

}
