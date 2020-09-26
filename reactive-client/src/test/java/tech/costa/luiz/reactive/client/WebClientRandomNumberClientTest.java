package tech.costa.luiz.reactive.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientRandomNumberClientTest {

    WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveRandomMessagesFromTheService() {
        // Given
        RandomNumberClient randomNumberClient = new WebClientRandomNumberClient(webClient);

        // When
        final Flux<Bingo> randomMessagesFor = randomNumberClient.randomNumbersFor("Luiz");

        // Then
        Assertions.assertNotNull(randomMessagesFor);
        final Flux<Bingo> messageFlux = randomMessagesFor.take(5);

        Assertions.assertEquals(5, messageFlux.count().block());
        Assertions.assertEquals("0", messageFlux.blockFirst().getId());
    }
}