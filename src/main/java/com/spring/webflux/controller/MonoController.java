package com.spring.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MonoController {

    @GetMapping(value = "/mono", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Integer> fetchMonoInteger() {
        return Mono.just(1).log();
    }
}
