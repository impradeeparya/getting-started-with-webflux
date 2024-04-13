package com.spring.webflux.router;

import com.spring.webflux.handler.FluxHandler;
import com.spring.webflux.handler.MonoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ApplicationRouter {


    @Bean
    public RouterFunction<ServerResponse> routeFlux(FluxHandler fluxHandler) {
        return RouterFunctions
                .route(
                        RequestPredicates
                                .GET("/functional/flux")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , fluxHandler::flux);
    }

    @Bean
    public RouterFunction<ServerResponse> routeMono(MonoHandler monoHandler) {
        return RouterFunctions
                .route(
                        RequestPredicates
                                .GET("/functional/mono")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , monoHandler::mono
                );
    }
}
