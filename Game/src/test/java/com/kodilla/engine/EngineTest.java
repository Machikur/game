package com.kodilla.engine;

import org.junit.Assert;
import org.junit.Test;

public class EngineTest {

    private final Engine easyEngine = new Engine(GameDifficult.EASY);
    private final Engine mediumEngine = new Engine(GameDifficult.MEDIUM);
    private final Engine hardEngine = new Engine(GameDifficult.HARD);

    @Test
    public void testDragonMoveEasyOne() {
        //given
        Engine engine = easyEngine;

        //when
        int value = engine.dragonTurn(15);

        //then
        Assert.assertEquals(1, value);
    }

    @Test
    public void testDragonMoveEasyTwo() {
        //given
        Engine engine = easyEngine;

        //when
        int value = engine.dragonTurn(14);

        //then
        Assert.assertEquals(2, value);
    }

    @Test
    public void testDragonMoveEasyThree() {
        //given
        Engine engine = easyEngine;

        //when
        int value = engine.dragonTurn(3);

        //then
        Assert.assertEquals(3, value);
    }

    @Test
    public void testDragonMoveEasyFour() {
        //given
        Engine engine = easyEngine;

        //when
        int value = engine.dragonTurn(2);

        //then
        Assert.assertEquals(2, value);
    }

    @Test
    public void testDragonMoveMediumOne() {
        //given
        Engine engine = mediumEngine;

        //when
        int value = engine.dragonTurn(3);

        //then
        Assert.assertEquals(3, value);
    }

    @Test
    public void testDragonMoveMediumTwo() {
        //given
        Engine engine = mediumEngine;

        //when
        int value = engine.dragonTurn(2);

        //then
        Assert.assertEquals(2, value);
    }

    @Test
    public void testDragonMoveMediumThree() {
        //given
        Engine engine = mediumEngine;

        //when
        int value = engine.dragonTurn(20);

        //then
        Assert.assertTrue(value > 0 && value < 4);
    }

    @Test
    public void testDragonMoveHardOne() {
        //given
        Engine engine = hardEngine;

        //when
        int value = engine.dragonTurn(21);

        //then
        Assert.assertEquals(1, value);
    }

    @Test
    public void testDragonMoveHardTwo() {
        //given
        Engine engine = hardEngine;

        //when
        int value = engine.dragonTurn(15);

        //then
        Assert.assertEquals(3, value);
    }

    @Test
    public void testDragonMoveHardThree() {
        //given
        Engine engine = hardEngine;

        //when
        int value = engine.dragonTurn(20);

        //then
        Assert.assertTrue(value > 0 && value < 4);
    }
}
