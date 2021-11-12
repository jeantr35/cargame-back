package co.com.sofka.cargame.usecases.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GetDice {

    public Integer getDice(){
        var rn = new Random();
        return 1 + rn.nextInt(6);
    }

}
