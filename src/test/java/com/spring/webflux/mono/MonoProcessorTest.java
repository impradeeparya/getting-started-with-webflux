package com.spring.webflux.mono;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class MonoProcessorTest {

    @Test
    void shouldSuffixFluxInInputData() {
        Mono<String> stringMono = Mono.just("Spring")
                .map(s -> s.concat(" flux"))
                .log();

        stringMono.subscribe(System.out::println);

        StepVerifier.create(stringMono)
                .expectNext("Spring flux")
                .verifyComplete();
    }

    @Test
    void shouldThrowRuntimeExceptionOnFailure() {
        StepVerifier.create(Mono.error(new RuntimeException("Exception occurred")).log())
                .expectError(RuntimeException.class)
                .verify();
    }
}
