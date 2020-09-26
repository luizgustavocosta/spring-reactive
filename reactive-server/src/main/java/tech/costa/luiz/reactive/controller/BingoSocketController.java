package tech.costa.luiz.reactive.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import tech.costa.luiz.reactive.model.Bingo;
import tech.costa.luiz.reactive.service.BingoService;

@Controller
@RequestMapping("/api/bingo")
public class BingoSocketController {

    private final BingoService service;

    public BingoSocketController(BingoService service) {
        this.service = service;
    }

    @MessageMapping("bingoNumbers")
    public Flux<Bingo> random(@PathVariable String name) {
        return service.generateNumbersFor(name);
    }
}
