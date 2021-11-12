package co.com.sofka.cargame.usecases.utils;

import co.com.sofka.cargame.collections.Driver;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveDriver {
    Mono<String> apply(Driver driver);
}
