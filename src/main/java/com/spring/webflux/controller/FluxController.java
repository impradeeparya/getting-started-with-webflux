package com.spring.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FluxController {

    @GetMapping("/flux")
    public Flux<Integer> fetchIntegerFlux() {
        return Flux.just(0, 1, 2, 3, 4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/flux-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> fetchIntegerFluxStream() {
        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }
}
