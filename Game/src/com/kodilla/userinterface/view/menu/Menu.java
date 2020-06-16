package com.kodilla.userinterface.view.menu;

import com.kodilla.datahandler.DataHandler;
import com.kodilla.datahandler.GameStatics;
import com.kodilla.engine.GameData;
import com.kodilla.engine.GameDifficult;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.buttons.ButtonsAndText;
import com.kodilla.userinterface.view.game.Game;
import com.kodilla.userinterface.view.ranking.Ranking;
import com.kodilla.userinterface.view.rules.Rules;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Menu {

    private BorderPane borderPane = new BorderPane();
    private Rules rules = new Rules();
    private DataHandler dataHandler = new DataHandler();
    private BackgroundScene backgroundScene = new BackgroundScene();
    private Scene menuScene = new Scene(borderPane, GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT, Color.WHITE);
    private Ranking ranking = new Ranking(menuScene, backgroundScene);
    private ArrayList<Button> difficultButtons = new ArrayList<>();
    private ButtonsAndText buttonsAndText = new ButtonsAndText();
    private GameData gameData = new GameData();
    private Stage primaryStage;
    private Game game = new Game(gameData, ranking, menuScene);
    private VBox menuButtons = setMenuButtons();

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        borderPane.setCenter(menuButtons);
        borderPane.setBackground(backgroundScene.getBackground());
        borderPane.setBottom(difficultyButtons());

        primaryStage.setScene(menuScene);
        primaryStage.show();

    }


    private HBox difficultyButtons() {
        HBox hBox = new HBox(80);

        for (GameDifficult gameDif : GameDifficult.values()) {
            Button button = buttonsAndText.newButton(gameDif.getName(), 200, 40);
            button.setOnAction(p -> {
                setDifficulty(gameDif);
                setButtonsColor();
                button.setStyle("-fx-background-color: chartreuse");
            });
            if (button.getText().equals(gameData.getGameDifficult().getName())) {
                button.setStyle("-fx-background-color: chartreuse");
            }

            hBox.getChildren().add(button);
            difficultButtons.add(button);
        }

        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }


    private void setDifficulty(GameDifficult gameDifficult) {
        gameData.setGameDifficult(gameDifficult);
    }

    private void setButtonsColor() {
        for (Button button : difficultButtons) {
            button.setStyle(GameStatics.DEFAULTBUTTONSTYLE);
        }
    }


    private VBox setMenuButtons() {
        VBox vBox = new VBox(30);

        Button start = buttonsAndText.newButton("Start new\n   game", 200, 100);
        start.setOnAction(param -> {
            gameData.setStartOfGame(gameData.getGameDifficult());
            game.game(primaryStage);
        });

        Button load = buttonsAndText.newButton("Load game", 200, 100);
        load.setOnAction(param -> {
            game.gameLoader(primaryStage);
        });


        Button rankingButton = buttonsAndText.newButton("Ranking", 200, 100);
        rankingButton.setOnAction(param ->
                ranking.getRanking(primaryStage));

        Button rulesButton = buttonsAndText.newButton("Show rules", 200, 100);
        rulesButton.setOnAction(param -> rules.getRules(primaryStage, menuScene));

        Button exit = buttonsAndText.newButton("Exit", 200, 100);
        exit.setOnAction(p -> {
            primaryStage.close();

        });

        vBox.getChildren().addAll(start, load, rankingButton, rulesButton, exit);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

}
