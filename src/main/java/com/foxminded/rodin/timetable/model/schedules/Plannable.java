package com.foxminded.rodin.timetable.model.schedules;

import java.time.LocalDate;

public interface Plannable {

    public boolean isAvailable(LocalDate begin, LocalDate end);

}
