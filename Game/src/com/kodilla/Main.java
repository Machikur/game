package com.kodilla;


import com.kodilla.userinterface.view.menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Menu menu = new Menu();
        menu.start(primaryStage);
    }
}
