package tech.costa.luiz.reactive.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Log4j2
public class RSocketRandomNumberClient implements RandomNumberClient{

    private final RSocketRequester requester;

    public RSocketRandomNumberClient(RSocketRequester requester) {
        this.requester = requester;
    }

    @Override
    public Flux<Bingo> randomNumbersFor(String name) {
        log.info("Name is {}", name);
        String route = "bingoNumbers"; // Defined on BingoSocketController
        return requester
                .route(route)
                .data(name)
                .retrieveFlux(Bingo.class)
                .doOnError(IOException.class, Throwable::printStackTrace);
    }
}
