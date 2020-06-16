package com.kodilla.engine;

import java.util.Random;

public class Engine {
    Random random = new Random();
    private GameDifficult gameDifficult;

    public Engine(GameDifficult gameDifficult) {
        this.gameDifficult = gameDifficult;
    }

    public int dragonTurn(int matchesValue) {
        if (gameDifficult == GameDifficult.HARD) {
            int result = matchesValue % 4;
            if (result == 0) {
                return random.nextInt(2) + 1;
            } else {
                return result;
            }
        }
        if (gameDifficult == GameDifficult.EASY) {
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
        if (gameDifficult == GameDifficult.MEDIUM) {
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
