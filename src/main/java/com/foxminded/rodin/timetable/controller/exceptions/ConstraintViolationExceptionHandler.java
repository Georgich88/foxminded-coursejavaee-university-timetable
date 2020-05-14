package com.foxminded.rodin.timetable.controller.exceptions;

import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView handleConstraintViolationException(ConstraintViolationException exp, HttpSession session) {
        ModelAndView response = new ModelAndView("error-422.html");
        if (exp != null) {
            response.addObject("errorList", exp.getConstraintViolations().stream()
                    .map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList()));
        }
        return response;
    }

}
