package co.com.sofka.cargame.usecases.utils;

import co.com.sofka.cargame.collections.Lane;
import co.com.sofka.cargame.model.NewGameDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveLane {
    Mono<String> apply(String gameId, String carId, Integer lengthKm);
}
