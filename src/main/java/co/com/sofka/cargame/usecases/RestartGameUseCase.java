package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.model.CarDTO;
import co.com.sofka.cargame.repositories.CarRepository;
import co.com.sofka.cargame.repositories.GameRepository;
import co.com.sofka.cargame.repositories.LaneRepository;
import co.com.sofka.cargame.usecases.utils.MapperUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
public class RestartGameUseCase implements Function<String, Flux<CarDTO>> {

    private final LaneRepository laneRepository;
    private final CarRepository carRepository;
    private final MapperUtils mapperUtils;
    private final CreateCarUseCase createCarUseCase;
    private final UpdateGameUseCase updateGameUseCase;
    private final GameRepository gameRepository;

    public RestartGameUseCase(LaneRepository laneRepository, CarRepository carRepository, MapperUtils mapperUtils, CreateCarUseCase createCarUseCase, UpdateGameUseCase updateGameUseCase, GameRepository gameRepository) {
        this.laneRepository = laneRepository;
        this.carRepository = carRepository;
        this.mapperUtils = mapperUtils;
        this.createCarUseCase = createCarUseCase;
        this.updateGameUseCase = updateGameUseCase;
        this.gameRepository= gameRepository;
    }

    @Override
    public Flux<CarDTO> apply(String gameId) {
        return laneRepository.findByGameId(gameId).flatMap(
                lane -> carRepository.findById(lane.getCarId()).flatMap(
                        car -> {
                            car.setDistance(0);
                            gameRepository.findById(gameId).flatMap(
                                    game -> {
                                        game.setPlaying(false);
                                        return updateGameUseCase.apply(game);
                                    }
                            );
                            return createCarUseCase.apply(car);
                        }
                )
        ).thenMany(
                carRepository.findAllByGameId(gameId).map(car -> {
                    return mapperUtils.mapperToCarDTO().apply(car);
                }));
    }
}
