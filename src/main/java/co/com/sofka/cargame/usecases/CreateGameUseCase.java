package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.collections.Game;
import co.com.sofka.cargame.model.NewGameDTO;
import co.com.sofka.cargame.repositories.GameRepository;
import co.com.sofka.cargame.usecases.utils.MapperUtils;
import co.com.sofka.cargame.usecases.utils.SaveGame;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateGameUseCase implements SaveGame {

    private final GameRepository gameRepository;
    private final MapperUtils mapperUtils;

    public CreateGameUseCase(GameRepository gameRepository, MapperUtils mapperUtils) {
        this.gameRepository = gameRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(NewGameDTO newGameDTO) {
        return gameRepository.save(mapperUtils.mapperToGame().apply(newGameDTO)).map(Game::getId);
    }
}
