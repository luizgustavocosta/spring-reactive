package tech.costa.luiz.reactive.domain.example;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Example service.
 */
@Service
public class ExampleService {

    private final static List<Message> messages = new ArrayList<>();
    private final ServiceId serviceId;

    /**
     * Instantiates a new Example service.
     *
     * @param serviceId the generate id
     */
    public ExampleService(ServiceId serviceId) {
        this.serviceId = serviceId;
    }


    /**
     * Init.
     */
    public void init() {
        int initialValue = 0;
        int maxValue = 10;
        while (initialValue < maxValue) {
            initialValue++;
            messages.add(Message.MessageBuilder.aMessage()
                    .withId(serviceId.getNextStringId())
                    .withMessage("Message init " + initialValue)
                    .build());
        }
    }

    /**
     * Stream flux.
     *
     * @return the flux
     */
    public Flux<Message> stream() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(this::createMessage)
                .takeUntil(message -> message.getId().endsWith("10"))
                //.log()
                .takeWhile(message -> !message.getId().equals("5"))
                .take(100)
                .take(Duration.ofSeconds(60));
    }

    /**
     * Create message message.
     *
     * @param sequence the sequence
     * @return the message
     */
    private Message createMessage(Long sequence) {
        final Message message = Message.MessageBuilder.aMessage()
                .withId(serviceId.getNextStringId())
                .withMessage("Stream message " + sequence)
                .build();
        messages.add(message);
        return message;
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Find all flux.
     *
     * @return the flux
     */
    public Flux<Message> findAll() {
        return Flux.fromIterable(messages);
    }

    /**
     * Gets message by.
     *
     * @param id the id
     * @return the message by
     */
    public Message getMessageBy(String id) {
        return getMessages().stream().filter(message -> message.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Try an error flux.
     *
     * @return the flux
     */
    public Flux<Message> tryAnError() {
        return Flux.fromArray(new String[]{"42", "", "84"})
                .map(ExampleService::createOneMessage)
                .map(ExampleService::save)
                .doOnError(throwable -> {})
                .onErrorContinue((throwable, o) -> {});
    }

    /**
     * Save message.
     *
     * @param message the message
     * @return the message
     */
    private static Message save(Message message) {
        messages.add(message);
        return message;
    }

    /**
     * Create one message message.
     *
     * @param valueAsString the value as string
     * @return the message
     */
    private static Message createOneMessage(String valueAsString) {
        if (valueAsString.isEmpty()) {
            return Message.MessageBuilder.aMessage()
                    .withId(null)
                    .withMessage(valueAsString)
                    .build();
        } else {
            return Message.MessageBuilder.aMessage()
                    .withId(valueAsString)
                    .withMessage("Message from createOne "+valueAsString)
                    .build();
        }
    }
}
