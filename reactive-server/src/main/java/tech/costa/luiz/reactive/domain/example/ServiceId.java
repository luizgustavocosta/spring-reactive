package tech.costa.luiz.reactive.domain.example;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Service id.
 */
@Service
public class ServiceId {

    private final AtomicInteger id = new AtomicInteger();

    /**
     * Gets next id.
     *
     * @return the next id
     */
    public int getNextId() {
        return id.incrementAndGet();
    }

    /**
     * Gets next string id.
     *
     * @return the next string id
     */
    public String getNextStringId() {
        return String.valueOf(id.incrementAndGet());
    }
}
