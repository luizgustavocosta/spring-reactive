package tech.costa.luiz.reactive.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bingo {

    private String id;
    private LocalDateTime dateTime;
    private Integer number;

}
