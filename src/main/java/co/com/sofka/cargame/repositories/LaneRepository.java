package co.com.sofka.cargame.repositories;

import co.com.sofka.cargame.collections.Lane;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LaneRepository extends ReactiveCrudRepository<Lane, String> {
}
