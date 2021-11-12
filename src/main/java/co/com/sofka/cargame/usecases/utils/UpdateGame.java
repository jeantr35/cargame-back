package co.com.sofka.cargame.usecases.utils;

import co.com.sofka.cargame.collections.Game;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UpdateGame {

    Mono<String> apply(Game game);

}
