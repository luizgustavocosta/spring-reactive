package tech.costa.luiz.reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @GetMapping(value = "/random/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<RandomMessage> random(@PathVariable String name) {
        return Flux.interval(Duration.ofSeconds(1))
                .map(number -> new RandomMessage(number.toString(), LocalDateTime.now(), name +" "+ number));
    }

}
