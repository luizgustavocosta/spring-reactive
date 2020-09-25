package tech.costa.luiz.reactive.client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

public class WebClientMessageClient {

    private WebClient webClient;

    public WebClientMessageClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<RandomMessage> randomMessagesFor(String name) {
        return webClient.get()
                .uri("http://localhost:8080/api/message/random/{name}", name)
                .retrieve()
                .bodyToFlux(RandomMessage.class)
                //.retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(5))
                .doOnError(IOException.class, error -> System.out.println("***"+error.getMessage()));
    }
}
