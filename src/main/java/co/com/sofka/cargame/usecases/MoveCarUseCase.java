package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.model.CarDTO;
import co.com.sofka.cargame.repositories.CarRepository;
import co.com.sofka.cargame.repositories.GameRepository;
import co.com.sofka.cargame.repositories.LaneRepository;
import co.com.sofka.cargame.usecases.utils.GetDice;
import co.com.sofka.cargame.usecases.utils.MapperUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;

@Service
public class MoveCarUseCase implements Function<String, Flux<CarDTO>> {

    private final LaneRepository laneRepository;
    private final CarRepository carRepository;
    private final CreateCarUseCase createCarUseCase;
    private final GetDice getDice;
    private final MapperUtils mapperUtils;
    private final GameRepository gameRepository;

    public MoveCarUseCase(LaneRepository laneRepository, CarRepository carRepository, CreateCarUseCase createCarUseCase, GetDice getDice, MapperUtils mapperUtils, GameRepository gameRepository) {
        this.laneRepository = laneRepository;
        this.carRepository = carRepository;
        this.createCarUseCase = createCarUseCase;
        this.getDice = getDice;
        this.mapperUtils = mapperUtils;
        this.gameRepository = gameRepository;
    }

    @Override
    public Flux<CarDTO> apply(String gameId) {
        return laneRepository.findByGameId(gameId).flatMap(
                lane -> carRepository.findById(lane.getCarId()).flatMap(
                        car -> {
                            if (car.getDistance() < lane.getLength()){
                                car.moveCar(getDice.getDice() * 100);
                            }else {
                                car.setDistance(lane.getLength());
                            }
                            return createCarUseCase.apply(car);
                        }
                )
        ).thenMany(
                carRepository.findAllByGameId(gameId).map(car -> {
                    return mapperUtils.mapperToCarDTO().apply(car);
                }));
    }
}
