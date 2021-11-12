package co.com.sofka.cargame.usecases;

import co.com.sofka.cargame.collections.Driver;
import co.com.sofka.cargame.repositories.DriverRepository;
import co.com.sofka.cargame.usecases.utils.SaveDriver;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateDriverUseCase implements SaveDriver {

    private final DriverRepository driverRepository;

    public CreateDriverUseCase(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Mono<String> apply(Driver driver) {
        return driverRepository.save(driver).map(Driver::getId);
    }
}
