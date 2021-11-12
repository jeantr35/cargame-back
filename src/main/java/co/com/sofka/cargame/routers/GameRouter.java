package co.com.sofka.cargame.routers;


import co.com.sofka.cargame.model.CarDTO;
import co.com.sofka.cargame.model.NewGameDTO;
import co.com.sofka.cargame.model.NewPlayerToGameDTO;
import co.com.sofka.cargame.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class GameRouter {

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

    @Bean
    public RouterFunction<ServerResponse> addPlayer(AddPlayerUseCase addPlayerUseCase) {
        return route(POST("/addPlayer").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(NewPlayerToGameDTO.class)
                        .flatMap(playerDTO -> addPlayerUseCase.apply(playerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> moveCars(MoveCarUseCase moveCarUseCase) {
        return route(POST("/movecars/{gameId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                moveCarUseCase.apply(request.pathVariable("gameId")),
                                CarDTO.class
                        ))
        );
    }

    @Bean RouterFunction<ServerResponse> toggleGame(ToggleGameUseCase toggleGameUseCase){
        return route(POST("/toggleplaygame/{gameId}/{state}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromPublisher(
                                toggleGameUseCase.apply(request.pathVariable("gameId"), Boolean.parseBoolean(request.pathVariable("state"))),
                                String.class
                        ))
        );
    }

    @Bean RouterFunction<ServerResponse> resetGame(RestartGameUseCase restartGameUseCase){
        return route(POST("/resetgame/{gameId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                restartGameUseCase.apply(request.pathVariable("gameId")),
                                CarDTO.class
                        ))
        );
    }

}