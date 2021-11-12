package co.com.sofka.cargame.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Podium {

    @Id
    private String id;
    private String gameId;
    private Player firstPlace;
    private Player secondPlace;
    private Player thirdPlace;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Player getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(Player firstPlace) {
        this.firstPlace = firstPlace;
    }

    public Player getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(Player secondPlace) {
        this.secondPlace = secondPlace;
    }

    public Player getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(Player thirdPlace) {
        this.thirdPlace = thirdPlace;
    }
}
