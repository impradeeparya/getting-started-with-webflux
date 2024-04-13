package com.spring.webflux.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@AutoConfigureWebTestClient(timeout = "6000")
class MonoControllerTest {

    @Autowired
    WebTestClient webTestClient;


    @Test
    void shouldFetchIntegerMono() {
        Integer expectedInteger = 1;
        webTestClient.get()
                .uri("/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith(integerEntityExchangeResult -> {
                    Assertions.assertEquals(expectedInteger, integerEntityExchangeResult.getResponseBody());
                });
    }
}
