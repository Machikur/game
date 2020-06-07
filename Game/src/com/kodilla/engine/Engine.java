package com.kodilla.engine;

import java.util.Random;

public class Engine {
    Random random = new Random();
    private int difficulty;

    public Engine(int difficulty) {
        this.difficulty = difficulty;
    }

    public int dragonTurn(int matchesValue) {
        if (difficulty == 2) {
            int result = matchesValue % 4;
            if (result == 0) {
                return random.nextInt(2) + 1;
            } else {
                return result;
            }
        }
        if (difficulty == 1) {
            int result = matchesValue % 2;
            if (matchesValue < 4) {
                return matchesValue;
            }
            if (result == 0) {
                return random.nextInt(2) + 1;
            } else {
                return result;
            }
        }
        if (difficulty == 0) {
            if (matchesValue < 4) {
                return matchesValue;
            } else {
                return random.nextInt(2) + 1;
            }
        } else {
            return 0;
        }
    }
}
