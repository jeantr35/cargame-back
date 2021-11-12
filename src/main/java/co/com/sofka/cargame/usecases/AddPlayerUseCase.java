package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.collections.Game;
import co.com.sofka.cargame.collections.Lane;
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
    private final CreateCarUseCase createCarUseCase;
    private final CreateLaneUseCase createLaneUseCase;

    public AddPlayerUseCase(GameRepository gameRepository, MapperUtils mapperUtils, UpdateGameUseCase updateGameUseCase, CreateDriverUseCase createDriverUseCase, CreateCarUseCase createCarUseCase, CreateLaneUseCase createLaneUseCase) {
        this.gameRepository = gameRepository;
        this.mapperUtils = mapperUtils;
        this.updateGameUseCase = updateGameUseCase;
        this.createDriverUseCase = createDriverUseCase;
        this.createCarUseCase = createCarUseCase;
        this.createLaneUseCase = createLaneUseCase;
    }


    @Override
    public Mono<String> apply(NewPlayerToGameDTO newPlayerToGameDTO) {
        return createDriverUseCase.apply(mapperUtils.mapperToDriver(null).apply(newPlayerToGameDTO))
                .flatMap(value -> createCarUseCase.apply(mapperUtils.mapperToCar(value).apply(newPlayerToGameDTO)).flatMap(
                        carValue -> gameRepository.findById(newPlayerToGameDTO.getGameId()).flatMap(
                                game -> createLaneUseCase.apply(game.getId(), carValue, game.getLenghtKm()*1000))
                            )
                ).thenReturn("Agregado");
    }
}
