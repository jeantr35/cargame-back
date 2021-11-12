package co.com.sofka.cargame.repositories;

import co.com.sofka.cargame.collections.Car;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CarRepository extends ReactiveCrudRepository<Car, String> {
    Flux<Car> findAllByGameId(String gameId);
}
