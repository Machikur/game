package com.kodilla.userinterface.view.menu;

import com.kodilla.datahandler.DataHandler;
import com.kodilla.datahandler.GameHandler;
import com.kodilla.engine.GameDifficult;
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
    private GameDifficult gameDifficult = GameDifficult.EASY;
    private Stage stage;
    private BorderPane borderPane = new BorderPane();
    private Rules rules = new Rules();
    private Ranking ranking = new Ranking();
    private DataHandler dataHandler = new DataHandler();
    private GameHandler gameHandler = new GameHandler();
    private Game game;


    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        BackgroundScene backgroundScene = new BackgroundScene();
        Scene scene = new Scene(borderPane, Game.SCENE_WIDTH, Game.SCENE_HEIGHT, Color.WHITE);

        VBox vBox = new VBox(30);

        Button start = newButton("Start new\n   game");
        start.setOnAction(param -> {
            game = new Game(gameDifficult, 0, 0, 21, ranking, scene);
            game.game(primaryStage);
        });

        Button load = newButton("Load last\n   game");
        load.setOnAction(param -> {
            String[] gameSave = gameHandler.LoadGame();
            if (gameSave.length == 3) {
                GameDifficult gameDifficult = GameDifficult.valueOf(gameSave[2]);
                int dragonScores = Integer.parseInt(gameSave[1]);
                int userScores = Integer.parseInt(gameSave[0]);
                game = new Game(gameDifficult, dragonScores, userScores, 21, ranking, scene);
                game.game(primaryStage);
            }
        });


        Button ranking = newButton("Ranking");
        ranking.setOnAction(param -> {
            this.ranking.getRanking(primaryStage, scene);
        });

        Button rulesButton = newButton("Show rules");
        rulesButton.setOnAction(param -> rules.getRules(primaryStage, scene));

        Button exit = newButton("Exit");
        exit.setOnAction(p -> {
            dataHandler.saveFile(this.ranking.getBestUsers(), this.ranking.getFile());
            gameHandler.saveGame(game);
            primaryStage.close();

        });

        vBox.getChildren().addAll(start, load, ranking, rulesButton, exit);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        borderPane.setBackground(backgroundScene.getBackground());
        borderPane.setBottom(difficultyButtons());
        difficultyText();


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
        easy.setOnAction(p -> setDifficulty(GameDifficult.EASY));
        easy.setPrefSize(200, 40);

        Button medium = newButton("Medium");
        medium.setOnAction(p -> setDifficulty(GameDifficult.MEDIUM));
        medium.setPrefSize(200, 40);

        Button hard = newButton("Hard");
        hard.setOnAction(p -> setDifficulty(GameDifficult.HARD));
        hard.setPrefSize(200, 40);

        hBox.getChildren().addAll(easy, medium, hard);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private void difficultyText() {
        Text text = new Text();
        if (gameDifficult.equals(GameDifficult.EASY)) {
            text.setText("Difficult Easy");
        }
        if (gameDifficult.equals(GameDifficult.MEDIUM)) {
            text.setText("Difficult Medium");
        }
        if (gameDifficult.equals(GameDifficult.HARD)) {
            text.setText("Difficult Hard");
        }
        text.setFont(Font.font(null, FontWeight.NORMAL, FontPosture.REGULAR, 20));
        text.setFill(Paint.valueOf("#FF0000"));

        borderPane.setTop(text);
    }

    public void setDifficulty(GameDifficult gameDifficult) {
        this.gameDifficult = gameDifficult;
        difficultyText();
    }


}
