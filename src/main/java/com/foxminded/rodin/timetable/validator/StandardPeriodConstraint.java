package com.foxminded.rodin.timetable.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { SchedulePeriodValidator.class })
public @interface StandardPeriodConstraint {
    String message() default "Invalid period";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
