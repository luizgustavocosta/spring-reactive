package tech.costa.luiz.reactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import tech.costa.luiz.reactive.model.Bingo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Bingo service.
 */
@Service
public class BingoService {

    /**
     * Generate numbers for flux.
     *
     * @param bingo the bingo
     * @return the flux
     */
    public Flux<Bingo> generateNumbersFor(String bingo) {
        return Flux.interval(Duration.ofSeconds(1))
                .map(number -> new Bingo(bingo, LocalDateTime.now(), randomNumber()));
    }

    private Integer randomNumber() {
        return ThreadLocalRandom.current().nextInt(100);
    }
}
