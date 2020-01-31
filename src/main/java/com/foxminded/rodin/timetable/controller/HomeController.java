package com.foxminded.rodin.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final String INDEX_FORM_RESOURSE_NAME = "index";

    @GetMapping("/")
    public String index() {
        return INDEX_FORM_RESOURSE_NAME;
    }
}
