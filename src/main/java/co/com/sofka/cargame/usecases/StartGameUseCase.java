package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.repositories.GameRepository;
import co.com.sofka.cargame.usecases.utils.MapperUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class StartGameUseCase implements Function<String, Mono<String>> {

    private final GameRepository gameRepository;
    private final MapperUtils mapperUtils;
    private final UpdateGameUseCase updateGameUseCase;

    public StartGameUseCase(GameRepository gameRepository, MapperUtils mapperUtils, UpdateGameUseCase updateGameUseCase) {
        this.gameRepository = gameRepository;
        this.mapperUtils = mapperUtils;
        this.updateGameUseCase = updateGameUseCase;
    }

    @Override
    public Mono<String> apply(String gameId) {
        return gameRepository.findById(gameId).flatMap(
                game -> {
                    game.setPlaying(true);
                    return updateGameUseCase.apply(game);
                }
        );
    }
}
