package co.com.sofka.cargame.model;

public class NewPlayerToGameDTO {

    private String id;
    private String gameId;
    private String name;
    private String username;
    private String carName;

    public NewPlayerToGameDTO(String id, String gameId, String name, String username, String carName) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.username = username;
        this.carName = carName;
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getCarName() {
        return carName;
    }
}
