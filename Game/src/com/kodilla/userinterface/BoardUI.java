package com.kodilla.userinterface;

import com.kodilla.engine.Engine;
import com.kodilla.view.background.BackgroundScene;
import com.kodilla.view.match.MatchesGridPane;
import com.kodilla.view.rules.Rules;
import com.kodilla.view.winner.Winner;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BoardUI {
    public final static int SCENE_WIDTH = 1024;
    public final static int SCENE_HEIGHT = 768;

    private int matchesValue = 21;


    private Engine engine = new Engine();
    private Winner winner = new Winner();
    private BorderPane borderPane = new BorderPane();
    private MatchesGridPane matchesGridPane = new MatchesGridPane();


    public void start(Stage primaryStage) {
        Rules rules = new Rules();
        Stage startStage = new Stage();
        startStage.setScene(rules.getRules());

        BackgroundScene backgroundScene = new BackgroundScene();
        borderPane.setBackground(backgroundScene.background());

        HBox hbox = addHBoxOfButtons();
        borderPane.setBottom(hbox);
        borderPane.setCenter(matchesGridPane.setGridPane(21));

        Scene scene = new Scene(borderPane, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        startStage.show();

    }


    private void divideMatchesValue(int value) {
        matchesValue -= value;
    }


    private Button operativeButton(String teskt, int value) {
        Engine engine = new Engine();
        double nodeWidth = SCENE_WIDTH / 10f;
        double nodeHeight = SCENE_HEIGHT / 10f;
        Button button = new Button();
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setText(teskt);
        button.setMinSize(nodeWidth, nodeHeight);
        button.setOnAction(param -> {
            if (matchesValue >= value) {
                divideMatchesValue(value);
                setMatchesValueView();
                if (matchesValue == 0) {
                    winner.setWinner("Użytkownik");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException s) {
                }
                divideMatchesValue(engine.dragonTurn(matchesValue));
                setMatchesValueView();
                if (matchesValue == 0) {
                    winner.setWinner("Smok");
                }
            } else button.setTextFill(Color.RED);
        });
        return button;
    }


    public HBox addHBoxOfButtons() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 100));
        hbox.setSpacing(100);
        hbox.setStyle("-fx-background-color: \t#808080;");

        Button button1 = operativeButton("Zabierz jedną zapałke", 1);
        button1.setPrefSize(200, 20);

        Button button2 = operativeButton("Zabierz dwie zapałki", 2);
        button1.setPrefSize(200, 20);

        Button button3 = operativeButton("Zabierz trzy zapałki", 3);
        button1.setPrefSize(200, 20);

        hbox.getChildren().addAll(button1, button2, button3);

        return hbox;
    }

    public void setMatchesValueView() {
        borderPane.setCenter(matchesGridPane.setGridPane(matchesValue));
    }

}
