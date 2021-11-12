package co.com.sofka.cargame.routers;


import co.com.sofka.cargame.model.NewGameDTO;
import co.com.sofka.cargame.usecases.CreateGameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> createGame(CreateGameUseCase createGameUseCase){

        Function<NewGameDTO, Mono<ServerResponse>> executor = newGameDTO -> createGameUseCase.apply(newGameDTO)
                .flatMap(result -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/createGame").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(NewGameDTO.class).flatMap(executor)
        );
    }

}