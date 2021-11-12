package co.com.sofka.cargame.repositories;

import co.com.sofka.cargame.collections.Game;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface GameRepository extends ReactiveCrudRepository<Game,String> {

}
