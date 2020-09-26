package tech.costa.luiz.reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import tech.costa.luiz.reactive.model.Bingo;
import tech.costa.luiz.reactive.service.BingoService;

@RestController
@RequestMapping("/api/bingo")
public class BingoController {

    private final BingoService service;

    public BingoController(BingoService service) {
        this.service = service;
    }

    @GetMapping(value = "/random/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Bingo> random(@PathVariable String name) {
        return service.generateNumbersFor(name);
    }
}
