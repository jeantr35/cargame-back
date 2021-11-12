package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.collections.Lane;
import co.com.sofka.cargame.repositories.LaneRepository;
import co.com.sofka.cargame.usecases.utils.MapperUtils;
import co.com.sofka.cargame.usecases.utils.SaveLane;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateLaneUseCase implements SaveLane {

    private final LaneRepository laneRepository;
    private final MapperUtils mapperUtils;

    public CreateLaneUseCase(LaneRepository laneRepository, MapperUtils mapperUtils) {
        this.laneRepository = laneRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(String gameId, String carId, Integer lengthKm) {
        return laneRepository.save(mapperUtils.mapperToLane(gameId, lengthKm).apply(carId)).map(Lane::getId);
    }
}
