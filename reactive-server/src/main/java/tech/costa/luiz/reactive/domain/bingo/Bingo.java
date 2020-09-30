package tech.costa.luiz.reactive.domain.bingo;

import java.time.LocalDateTime;

public class Bingo {

    private String id;
    private LocalDateTime dateTime;
    private Integer number;

    public Bingo(String id, LocalDateTime dateTime, Integer number) {
        this.id = id;
        this.dateTime = dateTime;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Integer getNumber() {
        return number;
    }
}
