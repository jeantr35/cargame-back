package co.com.sofka.cargame.usecases.utils;

import co.com.sofka.cargame.collections.Game;
import co.com.sofka.cargame.model.NewGameDTO;
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

}
