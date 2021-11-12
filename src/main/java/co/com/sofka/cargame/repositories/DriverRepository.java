package co.com.sofka.cargame.repositories;

import co.com.sofka.cargame.collections.Driver;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DriverRepository extends ReactiveCrudRepository<Driver, String> {
}
