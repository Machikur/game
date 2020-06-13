package com.kodilla.engine;

import java.io.Serializable;
import java.time.LocalDate;

public class GameData implements Serializable {
    private int userScore = 0;
    private int dragonScore = 0;
    private GameDifficult gameDifficult = GameDifficult.EASY;
    private LocalDate localDate;

    public GameData(int userScore, int dragonScore, GameDifficult gameDifficult) {
        this.userScore = userScore;
        this.dragonScore = dragonScore;
        this.gameDifficult = gameDifficult;
        localDate = LocalDate.now();
    }

    public GameData() {
        localDate = LocalDate.now();
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

}
