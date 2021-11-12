package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.collections.Car;
import co.com.sofka.cargame.repositories.CarRepository;
import co.com.sofka.cargame.usecases.utils.MapperUtils;
import co.com.sofka.cargame.usecases.utils.SaveCar;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateCarUseCase implements SaveCar {

    private final CarRepository carRepository;
    private final MapperUtils mapperUtils;

    public CreateCarUseCase(CarRepository carRepository, MapperUtils mapperUtils) {
        this.carRepository = carRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(Car car) {
        return carRepository.save(car).map(Car::getId);
    }
}
