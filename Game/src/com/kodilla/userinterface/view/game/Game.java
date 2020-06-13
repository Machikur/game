package com.kodilla.userinterface.view.game;

import com.kodilla.datahandler.GameStatics;
import com.kodilla.engine.Engine;
import com.kodilla.engine.GameData;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.buttons.ButtonsAndText;
import com.kodilla.userinterface.view.loadgame.GameLoader;
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
    private int matchesValue = 21;
    private Random random = new Random();
    private Stage primaryStage;
    private Ranking ranking;
    private Scene menuScene;
    private BackgroundScene backgroundScene = new BackgroundScene();
    private ButtonsAndText buttonsAndText = new ButtonsAndText();
    private GameData gameData;
    private Button[] operativeButtons = getOperativeButtons();
    private GameLoader gameLoader;

    public Game(GameData gameData, Ranking ranking, Scene menuScene, GameLoader gameLoader) {
        this.gameData = gameData;
        this.engine = new Engine(gameData.getGameDifficult());
        this.ranking = ranking;
        this.menuScene = menuScene;
        this.gameLoader = gameLoader;
        setGameScene();
    }

    public void game(Stage primaryStage) {
        matchesValue = GameStatics.DEFAULT_MATCHES_VALUE;
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
        Text text = new Text(GameStatics.DRAGON_NAME + " się zastanawia");
        text.setFont(Font.font(null, FontWeight.NORMAL, FontPosture.REGULAR, 30));
        VBox vBox = new VBox();
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.2));
        pauseTransition.setOnFinished(param -> {
            if (text.getText().length() > 21) {
                text.setText(GameStatics.DRAGON_NAME + " się zastanawia");
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
        winnerText.setText("Wygrał: " + winner + ", Wynik : " + gameData.getUserScore() + "-" + gameData.getDragonScore());
        winnerText.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        if (winner.equals(GameStatics.DRAGON_NAME)) {
            winnerText.setFill(Color.RED);
        } else {
            winnerText.setFill(Color.BLUE);
        }

        BorderPane textPane = new BorderPane();
        textPane.setBackground(backgroundScene.getBackgroundForMatches());
        textPane.setCenter(winnerText);
        BorderPane.setAlignment(winnerText, Pos.CENTER);

        if (gameData.getUserScore() == 5) {
            ranking.addUser(new UserScore(GameStatics.USER_NAME, gameData.getUserScore(), gameData.getDragonScore(), gameData.getGameDifficult()));
            winnerText.setText("Brawo, wygrałeś z wynikiem " + gameData.getUserScore() + "-" + gameData.getDragonScore());
            gameData.setUserScore(0);
            gameData.setDragonScore(0);
        }

        Button menu = buttonsAndText.newButton("Menu", 200, 20);
        menu.setOnAction(p -> {
            primaryStage.setScene(menuScene);
            primaryStage.show();
        });

        Button restart = buttonsAndText.newButton("Restart", 200, 20);
        restart.setOnAction(p -> {
            setNormalColorOfButtons();
            setGameScene();
            game(primaryStage);
            setMatchesValueView();
        });

        Button saveAndExit = buttonsAndText.newButton("Save and Exit", 200, 20);
        saveAndExit.setOnAction(p -> {
            gameLoader.addGameData(gameData);
            primaryStage.setScene(menuScene);
            primaryStage.show();

        });

        HBox hBox = new HBox();
        hBox.setSpacing(50);
        hBox.getChildren().addAll(menu, restart, saveAndExit);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: \t#808080;");

        borderPane.setBottom(hBox);
        borderPane.setCenter(textPane);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    private void setGameScene() {

        borderPane.setTop(backgroundScene.getImageView());
        borderPane.setMaxSize(SCENE_WIDTH, SCENE_HEIGHT);

        HBox hbox = buttonsAndText.addHBoxOfButtons(operativeButtons);
        borderPane.setBottom(hbox);
        borderPane.setCenter(matchesHBox.gethBox(matchesValue));

    }

    private Button[] getOperativeButtons() {
        Button one = buttonsAndText.newButton("Zabierz jedną zapałke", 250, 40);
        one.setOnAction(setTakeSomeMatchesActionEvent(1, one));
        Button two = buttonsAndText.newButton("Zabierz dwie zapałki", 250, 40);
        two.setOnAction(setTakeSomeMatchesActionEvent(2, two));
        Button thre = buttonsAndText.newButton("Zabierz trzy zapałki", 250, 40);
        thre.setOnAction(setTakeSomeMatchesActionEvent(3, thre));
        return new Button[]{one, two, thre};
    }

    private void userTurn(int value) {
        substractMatchesValue(value);
        setMatchesValueView();
        if (matchesValue == 0) {
            gameData.setUserScore(gameData.getUserScore() + 1);
            setWinner(GameStatics.USER_NAME);
        }
    }

    private void dragonTurn() {
        blockButtons();
        Stage stage = getStatement();
        stage.show();
        double time = random.nextInt(10) / 10f;
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(time + 0.5));
        pauseTransition.setOnFinished(event -> {
            substractMatchesValue(engine.dragonTurn(matchesValue));
            setMatchesValueView();
            if (matchesValue == 0) {
                stage.close();
                gameData.setDragonScore(gameData.getDragonScore() + 1);
                setWinner(GameStatics.DRAGON_NAME);
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

    private void setNormalColorOfButtons() {
        for (Button operativeButton : operativeButtons)
            operativeButton.setTextFill(Color.BLACK);
    }
}
