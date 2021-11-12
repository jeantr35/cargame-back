package co.com.sofka.cargame.usecases.utils;

import co.com.sofka.cargame.collections.Car;
import co.com.sofka.cargame.collections.Driver;
import co.com.sofka.cargame.collections.Game;
import co.com.sofka.cargame.collections.Lane;
import co.com.sofka.cargame.model.CarDTO;
import co.com.sofka.cargame.model.GameDTO;
import co.com.sofka.cargame.model.NewGameDTO;
import co.com.sofka.cargame.model.NewPlayerToGameDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<NewGameDTO, Game> mapperToGame(){

        return newGameDTO -> {
            var game = new Game();
            game.setId(newGameDTO.getId());
            game.setLenghtKm(newGameDTO.getLenghtKm());
            game.setNumPlayers(newGameDTO.getNumPlayers());
            game.setPlaying(false);
            game.setFinished(true);
            game.setDate(LocalDate.now());
            game.setPlayerList(new ArrayList<>());
            return game;
        };

    }

    public Function<NewPlayerToGameDTO, Driver> mapperToDriver(String id){
        return newDriver -> {
            var driver = new Driver();
            driver.setId(id);
            driver.setPlayerUsername(newDriver.getUsername());
            driver.setGameId(newDriver.getGameId());
            driver.setPosition(0);
            return driver;
        };
    }

    public Function<NewPlayerToGameDTO, Car> mapperToCar(String id){
        return newCar -> {
            var car = new Car();
            car.setId(null);
            car.setDistance(0);
            car.setGameId(newCar.getGameId());
            car.setDriverId(id);
            car.setName(newCar.getCarName());
            return car;
        };

    }

    public Function<String, Lane> mapperToLane(String gameId, Integer lengthKm){
        return carId -> {
            var lane = new Lane();
            lane.setLength(lengthKm);
            lane.setCarId(carId);
            lane.setGameId(gameId);
            lane.setId(null);
            return lane;
        };
    }

    public Function<Game, GameDTO> mapperToGameDTO(){
        return game ->
             new GameDTO(
                    game.getId(),
                    game.getLenghtKm(),
                    game.getNumPlayers(),
                    game.getPlaying(),
                    game.getFinished(),
                    game.getDate()
            );
    }

    public Function<Car, CarDTO> mapperToCarDTO(){
        return car -> new CarDTO(
                car.getGameId(),
                car.getName(),
                car.getDistance()
        );
    }

}
