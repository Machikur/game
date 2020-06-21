package com.kodilla.engine;

public enum GameDifficult {
    EASY("Easy"), MEDIUM("Medium"), HARD("Hard");

    private String name;

    GameDifficult(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
