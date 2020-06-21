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
        localDate = LocalDate.now();
        points = scoreDragon - scoreUser;
        this.gameDifficult = gameDifficult;
    }

    @Override
    public String toString() {
        return "Użytkownik: " + name + ", Wynik: " + scoreUser + "-" + scoreDragon + " " + localDate + " Poziom trudności: " + gameDifficult;
    }

    public int getPoints() {
        return points;
    }
}
