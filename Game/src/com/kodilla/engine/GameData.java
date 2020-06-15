package com.kodilla.engine;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class GameData implements Serializable, Comparable {
    private int userScore = 0;
    private int dragonScore = 0;
    private GameDifficult gameDifficult = GameDifficult.EASY;
    private LocalDate localDate;

    public GameData() {
        localDate = LocalDate.now();
    }

    public GameData(int userScore, int dragonScore, GameDifficult gameDifficult) {
        this.userScore = userScore;
        this.dragonScore = dragonScore;
        this.gameDifficult = gameDifficult;
        this.localDate = LocalDate.now();
    }

    public void setStartOfGame(GameDifficult gameDifficult) {
        userScore = 0;
        dragonScore = 0;
        this.gameDifficult = gameDifficult;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public int getDragonScore() {
        return dragonScore;
    }

    public void setDragonScore(int dragonScore) {
        this.dragonScore = dragonScore;
    }

    public GameDifficult getGameDifficult() {
        return gameDifficult;
    }

    public void setGameDifficult(GameDifficult gameDifficult) {
        this.gameDifficult = gameDifficult;
    }

    @Override
    public String toString() {
        return localDate + " , " + userScore + "-" + dragonScore + " , " + gameDifficult.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameData gameData = (GameData) o;
        return userScore == gameData.userScore &&
                dragonScore == gameData.dragonScore &&
                gameDifficult == gameData.gameDifficult &&
                Objects.equals(localDate, gameData.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userScore, dragonScore, gameDifficult, localDate);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof GameData) {
            return localDate.compareTo(((GameData) o).localDate);
        } else return 0;
    }
}
