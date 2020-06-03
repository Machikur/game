package com.kodilla;

import com.kodilla.userinterface.BoardUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BoardUI boardUI = new BoardUI();
        boardUI.start(primaryStage);
    }
}
