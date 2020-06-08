package com.kodilla.userinterface;

import com.kodilla.engine.Engine;
import com.kodilla.engine.GameDifficult;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.match.MatchesHBox;
import com.kodilla.userinterface.view.ranking.Ranking;
import com.kodilla.userinterface.view.ranking.UserScore;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game{
    public final static int SCENE_WIDTH = 1024;
    public final static int SCENE_HEIGHT = 768;
    private final BorderPane borderPane = new BorderPane();
    private final MatchesHBox matchesHBox = new MatchesHBox();
    private final Engine engine;
    private final Scene gameScene =new Scene(borderPane,SCENE_WIDTH,SCENE_HEIGHT,Color.WHITE);
    private int matchesValue;
    private int userScore = 0;
    private int dragonScore = 0;
    private Stage primarystage;
    private Ranking ranking;
    private Scene menuScene;
    private GameDifficult gameDifficult;
    private BackgroundScene backgroundScene=new BackgroundScene();

    public Game(GameDifficult gameDifficult,int dragonScore,int userScore, int matchesValue, Ranking ranking, Scene menuScene) {
        this.gameDifficult=gameDifficult;
        engine = new Engine(gameDifficult);
        this.matchesValue = matchesValue;
        setGameScene(matchesValue);
        this.ranking = ranking;
        this.menuScene=menuScene;
        this.dragonScore=dragonScore;
        this.userScore=userScore;
    }





    public void game(Stage primaryStage) {
        this.primarystage = primaryStage;
        primaryStage.setScene(gameScene);
        primaryStage.show();

    }

    private void substractMatchesValue(int value) {
        matchesValue -= value;
    }

    private Button operativeButton(String tekst, int value) {
        Button button = new Button();
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setText(tekst);
        button.setOnAction(setTakeSomeMatchesActionEvent(value, button));
        button.setPrefSize(250, 40);
        return button;
    }

    private HBox addHBoxOfButtons() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 15, 0, 15));
        hbox.setSpacing(100);
        hbox.setStyle("-fx-background-color: \t#808080;");

        Button button1 = operativeButton("Zabierz jedną zapałke", 1);
        Button button2 = operativeButton("Zabierz dwie zapałki", 2);
        Button button3 = operativeButton("Zabierz trzy zapałki", 3);

        hbox.getChildren().addAll(button1, button2, button3);

        return hbox;
    }

    public void setMatchesValueView() {
        borderPane.setCenter(matchesHBox.gethBox(matchesValue));
    }

    private EventHandler<ActionEvent> setTakeSomeMatchesActionEvent(int value, Button button) {
        return (param -> {
            if (matchesValue >= value) {
                if (button.getTextFill().equals(Color.RED)) {
                    button.setTextFill(Color.BLACK);
                }
                substractMatchesValue(value);
                setMatchesValueView();
                if (matchesValue == 0) {
                    userScore++;
                    setWinner("Użytkownik");

                }
                if (matchesValue > 0) {
                    Stage stage = getStatement();
                    stage.show();
                    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.5));
                    pauseTransition.setOnFinished(event -> {
                        substractMatchesValue(engine.dragonTurn(matchesValue));
                        setMatchesValueView();
                        if (matchesValue == 0) {
                            stage.close();
                            dragonScore++;
                            setWinner("Smok");

                        }
                        stage.close();
                    });
                    pauseTransition.play();
                }

            } else button.setTextFill(Color.RED);
        });
    }


    private Stage getStatement() {
        Text text = new Text("Kolej Smoka");
        text.setFont(Font.font(null, FontWeight.NORMAL, FontPosture.REGULAR, 30));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(text);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, Game.SCENE_WIDTH / 3f, Game.SCENE_HEIGHT / 7f, Color.WHITE);
        Stage stage = new Stage();
        stage.setScene(scene);

        return stage;
    }

    private void setWinner(String winner) {
        Text winnerText = new Text();
        winnerText.setText("Wygrał: " + winner + ", Wynik : " + userScore + "-" + dragonScore);
        winnerText.setFont(Font.font(null, FontWeight.NORMAL, FontPosture.REGULAR, 30));
        winnerText.setFill(Color.RED);


        if (userScore == 5) {
            ranking.addUser(new UserScore("Gracz", userScore, dragonScore,gameDifficult));
            winnerText.setText("Brawo, wygrałeś z wynikiem " + userScore + "-" + dragonScore);
            userScore = 0;
            dragonScore = 0;

        }

        Button menu = newButton("Menu");
        menu.setOnAction(p -> {
            primarystage.setScene(menuScene);
            primarystage.show();
        });

        Button restart = newButton("Restart");
        restart.setOnAction(p -> {
            setGameScene(21);
            game(primarystage);
            setMatchesValueView();

        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(menu, restart);
        hBox.setAlignment(Pos.CENTER);

        borderPane.setBottom(hBox);


        borderPane.setCenter(winnerText);
        primarystage.setScene(gameScene);
        primarystage.show();
    }

    private Button newButton(String text) {
        Button button = new Button();
        button.setPrefSize(200, 20);
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setText(text);
        return button;
    }

    private void setGameScene(int matchesValue) {
        this.matchesValue=matchesValue;
        BackgroundScene backgroundScene = new BackgroundScene();

        borderPane.setTop(backgroundScene.getImageView());
        borderPane.setMaxSize(SCENE_WIDTH, SCENE_HEIGHT);
        HBox hbox = addHBoxOfButtons();
        borderPane.setBottom(hbox);
        borderPane.setCenter(matchesHBox.gethBox(matchesValue));



    }


    public int getUserScore() {
        return userScore;
    }

    public int getDragonScore() {
        return dragonScore;
    }

    public GameDifficult getGameDifficult() {
        return gameDifficult;
    }
}
