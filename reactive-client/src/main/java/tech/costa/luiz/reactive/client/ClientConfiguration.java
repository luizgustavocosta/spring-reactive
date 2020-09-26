package tech.costa.luiz.reactive.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The type Client configuration.
 */
@Configuration
public class ClientConfiguration {

    @Autowired
    private RSocketRequester.Builder builder;

    /**
     * Web client message client random number client.
     *
     * @param webClient the web client
     * @return the random number client
     */
    @Bean
    @Profile("sse")
    public RandomNumberClient webClientMessageClient(WebClient webClient) {
        return new WebClientRandomNumberClient(webClient);
    }

    /**
     * Web client web client.
     *
     * @return the web client
     */
    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    /**
     * R socket client random number client.
     *
     * @return the random number client
     */
    @Bean
    @Profile("rsocket")
    public RandomNumberClient rSocketClient() {
        return new RSocketRandomNumberClient(rSocketRequester());
    }

    /**
     * R socket requester r socket requester.
     *
     * @return the r socket requester
     */
    @Bean
    public RSocketRequester rSocketRequester() {
        return builder.connectTcp("localhost", 7_000).block();
    }

}
