package co.com.sofka.cargame.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class Game {

    @Id
    private String id;
    private Integer lenghtKm;
    private Integer numPlayers;
    private Boolean playing;
    private Boolean finished;
    private LocalDate date;
    private List<Driver> playerList;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLenghtKm() {
        return lenghtKm;
    }

    public void setLenghtKm(Integer lenghtKm) {
        this.lenghtKm = lenghtKm;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public Boolean getPlaying() {
        return playing;
    }

    public void setPlaying(Boolean playing) {
        this.playing = playing;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public List<Driver> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Driver> playerList) {
        this.playerList = playerList;
    }

    public void addDriver(Driver driver){
        this.playerList.add(driver);
    }
}
