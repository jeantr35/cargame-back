package co.com.sofka.cargame.usecases.utils;

import co.com.sofka.cargame.model.NewGameDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveGame {
    Mono<String> apply(NewGameDTO newGameDTO);
}
