package com.kodilla.userinterface.view.game;

import com.kodilla.engine.Engine;
import com.kodilla.engine.GameDifficult;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.buttons.Buttons;
import com.kodilla.userinterface.view.matches.MatchesHBox;
import com.kodilla.userinterface.view.ranking.Ranking;
import com.kodilla.userinterface.view.ranking.UserScore;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.util.Random;

public class Game {
    public final static int SCENE_WIDTH = 1024;
    public final static int SCENE_HEIGHT = 768;
    private final BorderPane borderPane = new BorderPane();
    private final MatchesHBox matchesHBox = new MatchesHBox();
    private final Engine engine;
    private final Scene gameScene = new Scene(borderPane, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
    Random random = new Random();
    private int matchesValue;
    private int userScore;
    private int dragonScore;
    private Stage primaryStage;
    private Ranking ranking;
    private Scene menuScene;
    private GameDifficult gameDifficult;
    private BackgroundScene backgroundScene = new BackgroundScene();
    private Buttons buttons = new Buttons();
    private Button[] operativeButtons = getOperativeButtons();

    public Game(GameDifficult gameDifficult, int dragonScore, int userScore, int matchesValue, Ranking ranking, Scene menuScene) {
        this.gameDifficult = gameDifficult;
        engine = new Engine(gameDifficult);
        this.matchesValue = matchesValue;
        setGameScene(matchesValue);
        this.ranking = ranking;
        this.menuScene = menuScene;
        this.dragonScore = dragonScore;
        this.userScore = userScore;
    }

    public void game(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setScene(gameScene);
        primaryStage.show();

    }

    private void substractMatchesValue(int value) {
        matchesValue -= value;
    }

    public void setMatchesValueView() {
        borderPane.setCenter(matchesHBox.gethBox(matchesValue));
    }

    private EventHandler<ActionEvent> setTakeSomeMatchesActionEvent(int value, Button button) {
        return (param -> {
            if (matchesValue >= value) {
                userTurn(value);
                if (matchesValue > 0) {
                    dragonTurn();
                }

            } else button.setTextFill(Color.RED);
        });
    }


    private Stage getStatement() {
        Text text = new Text("Smok się zastanawia");
        text.setFont(Font.font(null, FontWeight.NORMAL, FontPosture.REGULAR, 30));
        VBox vBox = new VBox();
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.2));
        pauseTransition.setOnFinished(param -> {
            if (text.getText().length() > 21) {
                text.setText("Smok się zastanawia");
            } else {
                text.setText(text.getText() + ".");
            }
            pauseTransition.play();
        });
        vBox.getChildren().addAll(text);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, Game.SCENE_WIDTH / 3f, Game.SCENE_HEIGHT / 7f, Color.WHITE);
        Stage stage = new Stage();
        stage.setScene(scene);
        pauseTransition.play();

        return stage;
    }

    private void setWinner(String winner) {

        Text winnerText = new Text();
        winnerText.setText("Wygrał: " + winner + ", Wynik : " + userScore + "-" + dragonScore);
        winnerText.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        if (winner.equals("Smok")){
        winnerText.setFill(Color.RED);}
        else {
            winnerText.setFill(Color.BLUE);
        }

        BorderPane textPane = new BorderPane();
        textPane.setBackground(backgroundScene.getBackgroundForMatches());
        textPane.setCenter(winnerText);
        BorderPane.setAlignment(winnerText, Pos.CENTER);


        if (userScore == 5) {
            ranking.addUser(new UserScore("Gracz", userScore, dragonScore, gameDifficult));
            winnerText.setText("Brawo, wygrałeś z wynikiem " + userScore + "-" + dragonScore);
            userScore = 0;
            dragonScore = 0;

        }

        Button menu = buttons.newButton("Menu");
        menu.setOnAction(p -> {
            primaryStage.setScene(menuScene);
            primaryStage.show();
        });

        Button restart = buttons.newButton("Restart");
        restart.setOnAction(p -> {
            setNormalColorOfButtons();
            setGameScene(21);
            game(primaryStage);
            setMatchesValueView();
        });

        HBox hBox = new HBox();
        hBox.setSpacing(50);
        hBox.getChildren().addAll(menu, restart);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: \t#808080;");

        borderPane.setBottom(hBox);
        borderPane.setCenter(textPane);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }


    private void setGameScene(int matchesValue) {
        this.matchesValue = matchesValue;

        borderPane.setTop(backgroundScene.getImageView());
        borderPane.setMaxSize(SCENE_WIDTH, SCENE_HEIGHT);

        HBox hbox = buttons.addHBoxOfButtons(operativeButtons);
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

    private Button[] getOperativeButtons() {
        Button one = buttons.newButton("Zabierz jedną zapałke");
        one.setPrefSize(250,40);
        one.setOnAction(setTakeSomeMatchesActionEvent(1,one));
        Button two = buttons.newButton("Zabierz dwie zapałki");
        two.setOnAction(setTakeSomeMatchesActionEvent(2,two));
        two.setPrefSize(250,40);
        Button thre = buttons.newButton("Zabierz trzy zapałki");
        thre.setOnAction(setTakeSomeMatchesActionEvent(3,thre));
        thre.setPrefSize(250,40);
        return new Button[]{one, two, thre};
    }

    private void userTurn(int value) {
        substractMatchesValue(value);
        setMatchesValueView();
        if (matchesValue == 0) {
            userScore++;
            setWinner("Użytkownik");
        }
    }

    private void dragonTurn() {
        blockButtons();
        Stage stage = getStatement();
        stage.show();
        double time = random.nextInt(10) / 10f;
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(time+0.5));
        pauseTransition.setOnFinished(event -> {
            substractMatchesValue(engine.dragonTurn(matchesValue));
            setMatchesValueView();
            if (matchesValue == 0) {
                stage.close();
                dragonScore++;
                setWinner("Smok");

            }
            stage.close();
            unblockButtons();
        });
        pauseTransition.play();
    }

    private void blockButtons() {
        for (Button operativeButton : operativeButtons) {
            operativeButton.setDisable(true);
        }
    }

    private void unblockButtons() {
        for (Button operativeButton : operativeButtons) {
            operativeButton.setDisable(false);
        }
    }
    private void setNormalColorOfButtons(){
        for (Button operativeButton : operativeButtons)
            operativeButton.setTextFill(Color.BLACK);
    }
}
