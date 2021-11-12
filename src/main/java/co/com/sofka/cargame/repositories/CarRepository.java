package co.com.sofka.cargame.repositories;

import co.com.sofka.cargame.collections.Car;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CarRepository extends ReactiveCrudRepository<Car, String> {
}
