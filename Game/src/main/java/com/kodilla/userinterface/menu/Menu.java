package com.kodilla.userinterface.menu;

import com.kodilla.datahandler.GameStatics;
import com.kodilla.engine.GameData;
import com.kodilla.engine.GameDifficult;
import com.kodilla.engine.GameRanking;
import com.kodilla.userinterface.background.BackgroundScene;
import com.kodilla.userinterface.buttons.ButtonsAndText;
import com.kodilla.userinterface.game.Game;
import com.kodilla.userinterface.ranking.Ranking;
import com.kodilla.userinterface.rules.Rules;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Menu {

    private BorderPane borderPane = new BorderPane();
    private Rules rules = new Rules();
    private BackgroundScene backgroundScene = new BackgroundScene();
    private Scene menuScene = new Scene(borderPane, GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT, Color.WHITE);
    private Ranking ranking = new Ranking(menuScene, backgroundScene);
    private ArrayList<Button> difficultButtons = new ArrayList<>();
    private ButtonsAndText buttonsAndText = new ButtonsAndText();
    private GameData gameData = new GameData();
    private Stage primaryStage;
    private GameRanking gameRanking = new GameRanking();
    private String userName = "User";
    private Game game = new Game(gameData, menuScene, gameRanking, userName);


    public void start(Stage primaryStage) {

        this.game = new Game(gameData, menuScene, gameRanking, userName);
        this.primaryStage = primaryStage;
        this.borderPane.setCenter(setName());
        this.borderPane.setBackground(backgroundScene.getBackground());

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

        Text text = new Text(userName);
        buttonsAndText.getTextEffect(text);

        Button start = buttonsAndText.newButton("Start new\n   game", 200, 100);
        start.setOnAction(param -> {
            gameData.setStartOfGame();
            game.game(primaryStage);
        });

        Button load = buttonsAndText.newButton("Load game", 200, 100);
        load.setOnAction(param -> {
            game.gameLoader(primaryStage);
        });


        Button rankingButton = buttonsAndText.newButton("Ranking", 200, 100);
        rankingButton.setOnAction(param ->
                ranking.getRanking(primaryStage, gameRanking.bestUserToString()));

        Button rulesButton = buttonsAndText.newButton("Show rules", 200, 100);
        rulesButton.setOnAction(param -> rules.getRules(primaryStage, menuScene));

        Button exit = buttonsAndText.newButton("Exit", 200, 100);
        exit.setOnAction(p -> primaryStage.close());

        vBox.getChildren().addAll(text, start, load, rankingButton, rulesButton, exit);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    public VBox setName() {
        VBox vbox = new VBox();
        TextField textField = new TextField();
        textField.setText(userName);
        textField.setFont(Font.font(null, 30));
        textField.setAlignment(Pos.CENTER);
        borderPane.setCenter(textField);
        textField.setOnKeyPressed(key -> {
            if (key.getCode().equals(KeyCode.ENTER)) {
                userName = textField.getText();
                borderPane.setCenter(setMenuButtons());
                borderPane.setBottom(difficultyButtons());
            }
        });
        Text text = new Text("Put your name and press enter");
        buttonsAndText.getTextEffect(text);
        vbox.getChildren().addAll(text, textField);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxSize(200, 300);
        return vbox;
    }

}
