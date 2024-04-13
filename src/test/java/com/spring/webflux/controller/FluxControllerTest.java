package com.spring.webflux.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@AutoConfigureWebTestClient(timeout = "6000")
class FluxControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void shouldFetchIntegerFlux() {
        Flux<Integer> integerFlux = webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectNext(0)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .verifyComplete();
    }

    @Test
    void shouldFetchFluxOfSize5() {
        webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .hasSize(5);
    }

    @Test
    void shouldValidateReturnFluxResult() {

        List<Integer> expectedResult = Arrays.asList(0, 1, 2, 3, 4);
        EntityExchangeResult<List<Integer>> integerEntityExchangeResult = webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .returnResult();

        Assertions.assertEquals(expectedResult, integerEntityExchangeResult.getResponseBody());
    }

    @Test
    void shouldValidateReturnFluxResultConsumerWith() {

        List<Integer> expectedResult = Arrays.asList(0, 1, 2, 3, 4);
        EntityExchangeResult<List<Integer>> integerEntityExchangeResult = webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .consumeWith(entityExchangeResult -> {
                    Assertions.assertEquals(expectedResult, entityExchangeResult.getResponseBody());
                });


    }

    @Test
    void shouldFetchLongFluxStream() {
        Flux<Long> longFlux = webTestClient.get()
                .uri("/flux-stream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();

        StepVerifier.create(longFlux)
                .expectNext(0l)
                .expectNext(1l)
                .expectNext(2l)
                .expectNext(3l)
                .thenCancel()
                .verify();
    }

}
