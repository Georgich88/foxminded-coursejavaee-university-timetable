package com.foxminded.rodin.timetable.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.foxminded.rodin.timetable.model.schedules.Schedule;

public class SchedulePeriodValidator implements ConstraintValidator<StandardPeriodConstraint, Schedule> {

    @Override
    public boolean isValid(Schedule object, ConstraintValidatorContext context) {
        LocalDate startDate = object.getStartDate();
        LocalDate endDate = object.getEndDate();
        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }

}
