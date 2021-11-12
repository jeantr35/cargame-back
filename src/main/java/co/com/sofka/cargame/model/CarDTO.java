package co.com.sofka.cargame.model;

public class CarDTO {

    private String gameId;
    private String name;
    private Integer distance;

    public CarDTO(String gameId, String name, Integer distance) {
        this.gameId = gameId;
        this.name = name;
        this.distance = distance;
    }


    public String getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public Integer getDistance() {
        return distance;
    }
}
