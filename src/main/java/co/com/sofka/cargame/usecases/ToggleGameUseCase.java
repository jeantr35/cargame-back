package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.repositories.GameRepository;
import co.com.sofka.cargame.usecases.utils.MapperUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class ToggleGameUseCase {

    private final GameRepository gameRepository;
    private final UpdateGameUseCase updateGameUseCase;

    public ToggleGameUseCase(GameRepository gameRepository, UpdateGameUseCase updateGameUseCase) {
        this.gameRepository = gameRepository;
        this.updateGameUseCase = updateGameUseCase;
    }

    public Mono<String> apply(String gameId, Boolean value) {
        return gameRepository.findById(gameId).flatMap(
                game -> {
                    game.setPlaying(value);
                    return updateGameUseCase.apply(game);
                }
        );
    }
}
