package com.spring.webflux.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
class MonoHandlerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void shouldFetchIntegerFluxFromHandler() {
        Integer expectedInteger = 1;
        webTestClient.get()
                .uri("/functional/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith(integerFluxExchangeResult -> {
                    Assertions.assertEquals(expectedInteger, integerFluxExchangeResult.getResponseBody());
                });
    }
}
