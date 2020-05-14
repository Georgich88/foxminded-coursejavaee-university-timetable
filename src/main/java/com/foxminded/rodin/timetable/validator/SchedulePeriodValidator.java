package com.foxminded.rodin.timetable.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.foxminded.rodin.timetable.model.schedules.Schedule;

public class SchedulePeriodValidator implements ConstraintValidator<StandardPeriodConstraint, Schedule> {

    public SchedulePeriodValidator() {
    }

    public void initialize(StandardPeriodConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Schedule object, ConstraintValidatorContext context) {
        LocalDate startDate = object.getStartDate();
        LocalDate endDate = object.getEndDate();
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return false;
        } else {
            return true;
        }
    }

}
