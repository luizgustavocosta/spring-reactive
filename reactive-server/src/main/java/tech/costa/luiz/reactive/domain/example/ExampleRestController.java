package tech.costa.luiz.reactive.domain.example;


import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * The type Example rest controller.
 */
@RestController
@RequestMapping("api/example")
public class ExampleRestController {

    private final ExampleService service;

    /**
     * Instantiates a new Example controller.
     *
     * @param service the service
     */
    public ExampleRestController(ExampleService service) {
        this.service = service;
    }

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        service.init();
    }

    /**
     * Stream flux.
     *
     * @return the flux
     */
    @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> stream() {
        return service.stream();
    }

    /**
     * Find all flux.
     *
     * @return the flux
     */
    @GetMapping(value = "all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> findAll() {
        return service.findAll();
        //or
        //return Flux.from(getPublisher());
    }

    /**
     * Find by id mono.
     *
     * @param id the id
     * @return the mono
     */
    @GetMapping(value = "/stream/{id}")
    public Mono<Message> findById(@PathVariable String id) {
        // justOrEmpty to avoid error, since
        // just requires an object
        return Mono.justOrEmpty(service.getMessageBy(id));
    }

    /**
     * Raise an error flux.
     *
     * @return the flux
     */
    @GetMapping(value = "/stream/error", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> raiseAnError() {
        return service.tryAnError();
    }

    /**
     * Gets publisher.
     * Other way to an object to flux
     * @return the publisher
     */
    public Publisher<Message> getPublisher() {
        return Flux.fromIterable(service.getMessages());
    }
}
