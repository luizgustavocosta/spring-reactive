package tech.costa.luiz.reactive.client;

import reactor.core.publisher.Flux;

public interface RandomNumberClient {

    Flux<Bingo> randomNumbersFor(String name);
}
