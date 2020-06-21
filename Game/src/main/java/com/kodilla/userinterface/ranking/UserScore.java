package com.kodilla.userinterface.ranking;

import com.kodilla.engine.GameDifficult;

import java.io.Serializable;
import java.time.LocalDate;

public class UserScore implements Serializable {
    private String name;
    private LocalDate localDate;
    private int scoreUser;
    private int scoreDragon;
    private int points;
    private GameDifficult gameDifficult;

    public UserScore(String name, int scoreUser, int scoreDragon, GameDifficult gameDifficult) {
        this.name = name;
        this.scoreUser = scoreUser;
        this.scoreDragon = scoreDragon;
        this.localDate = LocalDate.now();
        this.points = scoreDragon - scoreUser;
        this.gameDifficult = gameDifficult;
    }

    @Override
    public String toString() {
        return "User: " + name + ", Score: " + scoreUser + "-" + scoreDragon + " " + localDate + " Level: " + gameDifficult;
    }

    public int getPoints() {
        return points;
    }
}
