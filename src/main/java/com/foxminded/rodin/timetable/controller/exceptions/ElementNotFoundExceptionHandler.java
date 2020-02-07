package com.foxminded.rodin.timetable.controller.exceptions;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ElementNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public String invalidTokenException(ElementNotFoundException exp, HttpSession session) {
        return "redirect:/error-404";
    }

}
