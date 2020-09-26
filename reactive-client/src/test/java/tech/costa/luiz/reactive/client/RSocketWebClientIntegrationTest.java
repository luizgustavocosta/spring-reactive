package tech.costa.luiz.reactive.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class RSocketWebClientIntegrationTest {

    @Autowired
    private RSocketRequester.Builder builder;

    @Test
    void shouldRetrieveRandomMessagesFromTheService() {
        // Given
        RSocketRandomNumberClient rSocketRandomNumberClient = new RSocketRandomNumberClient(createRSocketRequester());

        // When
        String bingoName = "Luiz";
        final Flux<Bingo> randomMessagesFor = rSocketRandomNumberClient.randomNumbersFor(bingoName);

        // Then
        Assertions.assertNotNull(randomMessagesFor);
        final Flux<Bingo> messageFlux = randomMessagesFor.take(5);

        Assertions.assertEquals(5, messageFlux.count().block());
        Assertions.assertEquals(bingoName, messageFlux.blockFirst().getId());

        //Other approach to test reactive stream
        StepVerifier.create(messageFlux.take(2))
                .expectNextMatches(bingo -> bingo.getId().equals(bingoName))
                .expectNextMatches(bingo -> bingo.getId().equals(bingoName))
                .verifyComplete();
    }

    private RSocketRequester createRSocketRequester() {
        return builder.connectTcp("localhost", 7_000).block();
    }
}