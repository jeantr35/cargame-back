package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.collections.Game;

import co.com.sofka.cargame.repositories.GameRepository;
import co.com.sofka.cargame.usecases.utils.MapperUtils;
import co.com.sofka.cargame.usecases.utils.UpdateGame;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateGameUseCase implements UpdateGame {

    private final GameRepository gameRepository;
    private final MapperUtils mapperUtils;

    public UpdateGameUseCase(GameRepository gameRepository, MapperUtils mapperUtils) {
        this.gameRepository = gameRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(Game game) {
        return gameRepository.save(game).map(Game::getId);
    }
}
