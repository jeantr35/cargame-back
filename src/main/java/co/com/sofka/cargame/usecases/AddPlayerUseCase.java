package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.model.NewGameDTO;
import co.com.sofka.cargame.model.NewPlayerToGameDTO;
import co.com.sofka.cargame.repositories.GameRepository;
import co.com.sofka.cargame.usecases.utils.MapperUtils;
import co.com.sofka.cargame.usecases.utils.SaveGame;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class AddPlayerUseCase implements Function<NewPlayerToGameDTO, Mono<String>> {

    private final GameRepository gameRepository;
    private final MapperUtils mapperUtils;
    private final UpdateGameUseCase updateGameUseCase;
    private final CreateDriverUseCase createDriverUseCase;

    public AddPlayerUseCase(GameRepository gameRepository, MapperUtils mapperUtils, UpdateGameUseCase updateGameUseCase, CreateDriverUseCase createDriverUseCase) {
        this.gameRepository = gameRepository;
        this.mapperUtils = mapperUtils;
        this.updateGameUseCase = updateGameUseCase;
        this.createDriverUseCase = createDriverUseCase;
    }

    @Override
    public Mono<String> apply(NewPlayerToGameDTO newPlayerToGameDTO) {
        return gameRepository.findById(newPlayerToGameDTO.getGameId()).flatMap(
                game -> {
                    createDriverUseCase.apply(mapperUtils.mapperToDriver(null).apply(newPlayerToGameDTO))
                            .subscribe(driverId -> {
                                game.addDriver(mapperUtils.mapperToDriver(driverId).apply(newPlayerToGameDTO));
                                gameRepository.save(game);
                            });
                    return updateGameUseCase.apply(game);
                }
        ).thenReturn("Agregado");
    }
}
