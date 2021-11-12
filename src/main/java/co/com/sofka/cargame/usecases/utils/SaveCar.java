package co.com.sofka.cargame.usecases.utils;

import co.com.sofka.cargame.collections.Car;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveCar {
    Mono<String> apply(Car car);
}
