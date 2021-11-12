package co.com.sofka.cargame.repositories;

import co.com.sofka.cargame.collections.Lane;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface LaneRepository extends ReactiveCrudRepository<Lane, String> {
    Flux<Lane> findByGameId(String gameId);
}
