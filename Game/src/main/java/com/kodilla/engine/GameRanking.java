package com.kodilla.engine;

import com.kodilla.datahandler.DataHandler;
import com.kodilla.datahandler.GameStatics;
import com.kodilla.userinterface.ranking.UserScore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GameRanking {

   private DataHandler dataHandler = new DataHandler();

    private List<UserScore> bestUsers;

    public GameRanking() {
        bestUsers = (ArrayList<UserScore>) dataHandler.loadFile(GameStatics.RANKING_PATH);
        if (Objects.isNull(bestUsers)) {
            bestUsers = new ArrayList<>();
        }
    }

    public void addUser(UserScore user) {
        if (bestUsers.size() < 10 || bestUsers.get(9).getPoints() < user.getPoints()) {
            bestUsers.add(user);
            bestUsers.sort(Comparator.comparingInt(UserScore::getPoints));
            dataHandler.saveFile(bestUsers, GameStatics.RANKING_PATH);
        }
    }

    public String bestUserToString() {
        if (bestUsers.size() == 0) {
            return "Brak pozycji w rankingu";
        } else {
            StringBuilder records = new StringBuilder();
            for (int i = 0; i < bestUsers.size(); i++) {
                records.append(i + 1).append(" ").append(bestUsers.get(i)).append("\n");
            }

            return records.toString();
        }
    }
}
