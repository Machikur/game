package com.kodilla.userinterface.view.ranking;

import java.time.LocalDate;

public class User {
    private String name;
    private LocalDate localDate;
    private int scoreUser;
    private int scoreDragon;
    private int points = scoreDragon - scoreDragon;

    public User(String name, int scoreUser, int scoreDragon) {
        this.name = name;
        this.scoreUser = scoreUser;
        this.scoreDragon = scoreDragon;
        localDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Użytkownik: " + name + ", Wynik: " + scoreUser + "-" + scoreDragon + " " + localDate;
    }

    public int getPoints() {
        return points;
    }
}
