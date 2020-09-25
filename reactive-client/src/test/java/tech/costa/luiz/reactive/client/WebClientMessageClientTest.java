package tech.costa.luiz.reactive.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientMessageClientTest {

    WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveRandomMessagesFromTheService() {
        // Given
        WebClientMessageClient webClientMessageClient = new WebClientMessageClient(webClient);

        // When
        final Flux<RandomMessage> randomMessagesFor = webClientMessageClient.randomMessagesFor("Luiz");

        // Then
        Assertions.assertNotNull(randomMessagesFor);
        final Flux<RandomMessage> messageFlux = randomMessagesFor.take(5);

        Assertions.assertEquals(5, messageFlux.count().block());
        Assertions.assertEquals("Luiz 0", messageFlux.blockFirst().getMessage());
    }
}