package com.foxminded.rodin.timetable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.foxminded.rodin.timetable" })
@EntityScan("com.foxminded.rodin.timetable.model")
@EnableJpaRepositories("com.foxminded.rodin.timetable.repo")
public class UniversityTimetableApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UniversityTimetableApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UniversityTimetableApplication.class);
    }

}
