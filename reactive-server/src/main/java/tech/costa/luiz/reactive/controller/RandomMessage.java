package tech.costa.luiz.reactive.controller;

import java.time.LocalDateTime;

public class RandomMessage {
    private String id;
    private LocalDateTime dateTime;
    private String message;

    public RandomMessage(String id, LocalDateTime dateTime, String message) {
        this.id = id;
        this.dateTime = dateTime;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }
}
