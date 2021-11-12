package co.com.sofka.cargame.model;

import java.time.LocalDate;

public class GameDTO {

    private String id;
    private Integer lenghtKm;
    private Integer numPlayers;
    private Boolean playing;
    private Boolean finished;
    private LocalDate date;

    public GameDTO(String id, Integer lenghtKm, Integer numPlayers, Boolean playing, Boolean finished, LocalDate date) {
        this.id = id;
        this.lenghtKm = lenghtKm;
        this.numPlayers = numPlayers;
        this.playing = playing;
        this.finished = finished;
        this.date = date;
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

    public Boolean getPlaying() {
        return playing;
    }

    public Boolean getFinished() {
        return finished;
    }

    public LocalDate getDate() {
        return date;
    }
}
