package com.kodilla.engine;

import com.kodilla.userinterface.ranking.UserScore;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GameRankingTest {

    @Test
    public void addNewUserTest() {
        //Given
        GameRanking gameRanking = new GameRanking();
        List<UserScore> list = gameRanking.getBestUsers();
        gameRanking.setBestUsers(cleanAndAddNewUsersToList(5, list));
        UserScore userScore = new UserScore("User", 5, 5, GameDifficult.EASY);

        //When
        int before = list.size();
        gameRanking.addUser(userScore);
        int after = list.size();

        //Then
        Assert.assertEquals(5, before);
        Assert.assertEquals(6, after);
        Assert.assertTrue(list.contains(userScore));

    }

    @Test
    public void addBetterUserWhenListIsMaxTest() {
        //Given
        GameRanking gameRanking = new GameRanking();
        gameRanking.setBestUsers(cleanAndAddNewUsersToList(10, gameRanking.getBestUsers()));
        UserScore userScore = new UserScore("User", 5, 0, GameDifficult.EASY);

        //When
        int before = gameRanking.getBestUsers().size();
        gameRanking.addUser(userScore);
        gameRanking.addUser(userScore);
        int after = gameRanking.getBestUsers().size();

        //Then
        Assert.assertEquals(10, before);
        Assert.assertEquals(10, after);
        Assert.assertTrue(gameRanking.getBestUsers().contains(userScore));

    }

    @Test
    public void addWorseUserWhenListIsMaxTest() {
        //Given
        GameRanking gameRanking = new GameRanking();
        gameRanking.setBestUsers(cleanAndAddNewUsersToList(10, gameRanking.getBestUsers()));
        UserScore userScore = new UserScore("User", 5, 15, GameDifficult.EASY);

        //When
        int before = gameRanking.getBestUsers().size();
        gameRanking.addUser(userScore);
        int after = gameRanking.getBestUsers().size();
        ;

        //Then
        Assert.assertEquals(10, before);
        Assert.assertEquals(10, after);
        Assert.assertFalse(gameRanking.getBestUsers().contains(userScore));

    }

    @Test
    public void sortTest() {
        //Given
        GameRanking gameRanking = new GameRanking();
        List<UserScore> list = gameRanking.getBestUsers();
        gameRanking.setBestUsers(cleanAndAddNewUsersToList(9, list));
        UserScore userScore = new UserScore("User", 10, 0, GameDifficult.EASY);

        //When
        gameRanking.addUser(userScore);
        int one = list.get(1).getPoints();
        int two = list.get(4).getPoints();
        int third = list.get(7).getPoints();
        ;

        //Then
        Assert.assertTrue(one < two && two < third);
        Assert.assertEquals(gameRanking.getBestUsers().get(0), (userScore));

        //??
        Assert.assertEquals(list.get(0), (userScore));

    }

    private List<UserScore> cleanAndAddNewUsersToList(int amount, List<UserScore> userScoreList) {
        userScoreList.clear();
        for (int i = 0; i < amount; i++) {
            userScoreList.add(new UserScore("User" + i, 5, i, GameDifficult.EASY));
        }
        return userScoreList;
    }
}
