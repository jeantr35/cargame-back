package co.com.sofka.cargame.model;

public class NewPlayerToGameDTO {

    private String id;
    private String gameId;
    private String name;
    private String username;

    public NewPlayerToGameDTO(String id, String gameId, String name, String username) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.username = username;
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
}
