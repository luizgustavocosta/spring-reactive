package tech.costa.luiz.reactive.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Log4j2
public class WebClientRandomNumberClient implements RandomNumberClient {

    private WebClient webClient;

    public WebClientRandomNumberClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<Bingo> randomNumbersFor(String name) {
        log.info("Using webclient with the name {}", name);
        return webClient.get()
                .uri("http://localhost:8080/api/bingo/random/{name}", name)
                .retrieve()
                .bodyToFlux(Bingo.class)
                //.retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(5))
                .doOnError(IOException.class, Throwable::printStackTrace);
    }
}
