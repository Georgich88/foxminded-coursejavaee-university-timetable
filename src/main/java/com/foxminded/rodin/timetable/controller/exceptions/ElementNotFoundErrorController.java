package com.foxminded.rodin.timetable.controller.exceptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ElementNotFoundErrorController implements ErrorController {

    @RequestMapping("/error-404")
    public String handleError() {
        return "error-404";
    }

    @Override
    public String getErrorPath() {
        return "/error-404";
    }

}
