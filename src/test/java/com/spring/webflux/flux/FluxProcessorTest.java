package com.spring.webflux.flux;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxProcessorTest {

    @Test
    void shouldSuffixFluxInInputData() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring Boot")
                .map(s -> s.concat(" flux"))
                .log();
//        stringFlux.subscribe(System.out::println,
//                e -> System.err.println("Exception occurred " + e),
//                () -> System.out.println("Completed"));

        StepVerifier.create(stringFlux)
                .expectNext("Spring flux")
                .expectNext("Spring Boot flux")
                .expectNext("Reactive Spring Boot flux")
                .verifyComplete();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void shouldThrowRuntimeExceptionOnFailure() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring Boot")
                .map(s -> s.concat(" flux"))
                .concatWith(Flux.error(new RuntimeException("Exception concatenated")))
                .log();
//        stringFlux.subscribe(System.out::println,
//                e -> System.err.println("Exception occurred " + e));

        StepVerifier.create(stringFlux)
                .expectNext("Spring flux")
                .expectNext("Spring Boot flux")
                .expectNext("Reactive Spring Boot flux")
                .expectError(RuntimeException.class)
                .verify();

        StepVerifier.create(stringFlux)
                .expectNext("Spring flux")
                .expectNext("Spring Boot flux")
                .expectNext("Reactive Spring Boot flux")
                .expectErrorMessage("Exception concatenated")
                .verify();
    }
}
