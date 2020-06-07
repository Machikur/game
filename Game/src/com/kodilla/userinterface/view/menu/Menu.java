package com.kodilla.userinterface.view.menu;

import com.kodilla.datahandler.DataHandler;
import com.kodilla.userinterface.Game;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.ranking.Ranking;
import com.kodilla.userinterface.view.rules.Rules;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Menu {
    private int difficulty = 0;
    private Stage stage;
    private BorderPane borderPane = new BorderPane();
    private Rules rules = new Rules();
    private Ranking ranking=new Ranking();


    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        BackgroundScene backgroundScene = new BackgroundScene();

        VBox vBox = new VBox(80);

        Button start = newButton("Start new\n   game");
        start.setOnAction(param -> {
            Game game = new Game(difficulty, 21, ranking);
            game.game(primaryStage);
        });

        Button load = newButton("Ranking");
        load.setOnAction(param -> {
            ranking.getRanking(primaryStage);
        });

        Button rulesButton = newButton("Show rules");
        rulesButton.setOnAction(param -> rules.getRules(primaryStage));

        Button exit = newButton("Exit");
        exit.setOnAction(p -> {
            DataHandler dataHandler = new DataHandler();
            dataHandler.saveFile(ranking.getBestUsers());
            primaryStage.close();

        });

        vBox.getChildren().addAll(start, load, rulesButton, exit);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        borderPane.setBackground(backgroundScene.getBackground());
        borderPane.setBottom(difficultyButtons());
        difficultyText();

        Scene scene = new Scene(borderPane, Game.SCENE_WIDTH, Game.SCENE_HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Button newButton(String string) {
        Button button = new Button();
        button.setFont(Font.font(null, FontWeight.LIGHT, FontPosture.REGULAR, 25));
        button.setPrefSize(200, 100);
        button.setText(string);
        return button;
    }


    private HBox difficultyButtons() {
        HBox hBox = new HBox(80);

        Button easy = newButton("Easy");
        easy.setOnAction(p -> setDifficulty(0));
        easy.setPrefSize(200, 40);

        Button medium = newButton("Medium");
        medium.setOnAction(p -> setDifficulty(1));
        medium.setPrefSize(200, 40);

        Button hard = newButton("Hard");
        hard.setOnAction(p -> setDifficulty(2));
        hard.setPrefSize(200, 40);

        hBox.getChildren().addAll(easy, medium, hard);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private void difficultyText() {
        Text text = new Text();
        if (difficulty == 0) {
            text.setText("Difficult Easy");
        }
        if (difficulty == 1) {
            text.setText("Difficult Medium");
        }
        if (difficulty == 2) {
            text.setText("Difficult Hard");
        }
        text.setFont(Font.font(null, FontWeight.NORMAL, FontPosture.REGULAR, 20));
        text.setFill(Paint.valueOf("#FF0000"));

        borderPane.setTop(text);
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        difficultyText();
    }


}
