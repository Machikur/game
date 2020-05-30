package com.kodilla.engine;

import java.util.Random;

public class Engine {
    Random random = new Random();

    public int dragonTurn(int matchesValue) {
        int result = matchesValue % 4;
        if (result == 0) {
            return random.nextInt(2) + 1;
        } else {
            return result;
        }
    }
}
