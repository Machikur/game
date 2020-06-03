package com.kodilla.userinterface;

import com.kodilla.engine.Engine;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.match.MatchesGridPane;
import com.kodilla.userinterface.view.rules.Rules;
import com.kodilla.userinterface.view.winner.Winner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private static Integer matchesValue = 21;


    private Engine engine = new Engine();
    private Winner winner = new Winner(matchesValue);
    private BorderPane borderPane = new BorderPane();
    private MatchesGridPane matchesGridPane = new MatchesGridPane();
    private Stage primaryStage = new Stage();


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Rules rules = new Rules();
        Stage startStage = rules.getStage();

        BackgroundScene backgroundScene = new BackgroundScene();

        borderPane.setBackground(backgroundScene.background());
        borderPane.setMaxSize(SCENE_WIDTH, SCENE_HEIGHT);
        HBox hbox = addHBoxOfButtons();
        borderPane.setBottom(hbox);
        borderPane.setCenter(matchesGridPane.gethBox(21));


        Scene scene = new Scene(borderPane, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        startStage.show();
    }


    private void substractMatchesValue(int value) {
        matchesValue -= value;
    }


    private Button operativeButton(String tekst, int value, int prefWidth, int prefHeight) {
        double nodeWidth = SCENE_WIDTH / 10f;
        double nodeHeight = SCENE_HEIGHT / 10f;
        Button button = new Button();
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setText(tekst);
        button.setMinSize(nodeWidth, nodeHeight);
        button.setOnAction(setTakeSomeMatchesActionEvent(value, button));
        button.setPrefSize(prefWidth, prefHeight);
        return button;
    }


    public HBox addHBoxOfButtons() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 100));
        hbox.setSpacing(100);
        hbox.setStyle("-fx-background-color: \t#808080;");

        Button button1 = operativeButton("Zabierz jedną zapałke", 1, 200, 20);
        Button button2 = operativeButton("Zabierz dwie zapałki", 2, 200, 20);
        Button button3 = operativeButton("Zabierz trzy zapałki", 3, 200, 20);
        hbox.getChildren().addAll(button1, button2, button3);

        return hbox;
    }

    public void setMatchesValueView() {
        borderPane.setCenter(matchesGridPane.setGridPane(matchesValue));
    }

    private EventHandler<ActionEvent> setTakeSomeMatchesActionEvent(int value, Button button) {
        return (param -> {
            if (matchesValue >= value) {
                substractMatchesValue(value);
                if (matchesValue == 0) {
                    winner.getWinner("Użytkownik");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException s) {
                }
                substractMatchesValue(engine.dragonTurn(matchesValue));
                if (matchesValue == 0) {
                    winner.getWinner("Smok");
                }
                setMatchesValueView();
            } else button.setTextFill(Color.RED);
        });
    }

    public void closeApp() {
        primaryStage.close();
    }

    public static void setMatchesValue() {
        matchesValue = 21;
    }

}
