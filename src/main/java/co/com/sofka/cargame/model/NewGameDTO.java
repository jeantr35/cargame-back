package co.com.sofka.cargame.model;

public class NewGameDTO {

    private String id;
    private Integer lenghtKm;
    private Integer numPlayers;

    public NewGameDTO(String id, Integer lenghtKm, Integer numPlayers) {
        this.id = id;
        this.lenghtKm = lenghtKm;
        this.numPlayers = numPlayers;
    }

    public String getId() {
        return id;
    }

    public Integer getLenghtKm() {
        return lenghtKm;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }
}
