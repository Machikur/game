package com.kodilla.datahandler;

import com.kodilla.userinterface.view.game.Game;

import java.io.File;

public class GameHandler {

    private File file = new File("Game/resources/game.txt");
    private DataHandler dataHandler = new DataHandler();

    public void saveGame(Game game) {
        String gameSave = game.getUserScore() + ";" + game.getDragonScore() + ";" + game.getGameDifficult();
        dataHandler.saveFile(gameSave, file);

    }

    public String[] LoadGame() {
        if (file.length() != 0) {
            String gameSave = (String) dataHandler.loadFile(file);
            return gameSave.split(";");
        } else return new String[0];

    }
}
