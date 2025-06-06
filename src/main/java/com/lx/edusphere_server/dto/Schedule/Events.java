package com.lx.edusphere_server.dto.Schedule;

import com.lx.edusphere_server.entity.Event;

import java.time.LocalDate;

public class Events {
    private LocalDate date;
    private Event event;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
