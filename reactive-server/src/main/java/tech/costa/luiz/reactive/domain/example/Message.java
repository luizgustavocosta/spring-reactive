package tech.costa.luiz.reactive.domain.example;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Message.
 */
public class Message {

    private final String id;
    private final LocalDateTime dateTime;
    private final String message;

    /**
     * Instantiates a new Message.
     *
     * @param id       the id
     * @param dateTime the date time
     * @param message  the message
     */
    public Message(String id, LocalDateTime dateTime, String message) {
        Objects.requireNonNull(id, "Id is a required field");
        Objects.requireNonNull(dateTime, "Datetime is a required field");
        Objects.requireNonNull(message, "Message is a required field");
        this.id = id;
        this.dateTime = dateTime;
        this.message = message;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets date time.
     *
     * @return the date time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }



    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", message='" + message + '\'' +
                '}';
    }

    public static final class MessageBuilder {
        private String id;
        private String message;

        private MessageBuilder() {
        }

        public static MessageBuilder aMessage() {
            return new MessageBuilder();
        }

        public MessageBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public MessageBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            return new Message(id, LocalDateTime.now(), message);
        }
    }
}
