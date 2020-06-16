package com.foxminded.rodin.timetable.model.schedules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.foxminded.rodin.timetable.validator.StandardPeriodConstraint;

@Entity
@DynamicUpdate
@Table(name = "schedules")
@StandardPeriodConstraint
public class Schedule {

    private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    private static final String ERROR_START_DATE_IS_MANDATORY_MESSAGE = "Start date is mandatory";
    private static final String ERROR_END_DATE_IS_MANDATORY_MESSAGE = "End date is mandatory";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Slot> slots;
    @NotNull(message = ERROR_START_DATE_IS_MANDATORY_MESSAGE)
    @DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
    private LocalDate startDate;
    @NotNull(message = ERROR_END_DATE_IS_MANDATORY_MESSAGE)
    @DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
    private LocalDate endDate;

    public Schedule() {
        this.name = "";
        this.slots = new ArrayList<Slot>();
    }

    public Schedule(String name, List<Slot> slots) {
        this.name = name;
        this.slots = slots;
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

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
