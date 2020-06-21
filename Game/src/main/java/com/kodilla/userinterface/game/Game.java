package com.kodilla.userinterface.game;

import com.kodilla.datahandler.GameStatics;
import com.kodilla.engine.Engine;
import com.kodilla.engine.GameData;
import com.kodilla.engine.GameDifficult;
import com.kodilla.engine.GameRanking;
import com.kodilla.userinterface.buttons.ButtonsAndText;
import com.kodilla.userinterface.loadgame.GameLoader;
import com.kodilla.userinterface.matches.MatchesHBox;
import com.kodilla.userinterface.ranking.UserScore;
import com.kodilla.userinterface.background.BackgroundScene;
import com.kodilla.sounds.*;
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
    private final BorderPane borderPane = new BorderPane();
    private final MatchesHBox matchesHBox = new MatchesHBox();
    private final Engine engine;
    private final Scene gameScene = new Scene(borderPane, GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT, Color.WHITE);
    private int matchesValue = GameStatics.DEFAULT_MATCHES_VALUE;
    private GameRanking gameRanking;
    private Random random = new Random();
    private Stage primaryStage;
    private Scene menuScene;
    private BackgroundScene backgroundScene = new BackgroundScene();
    private ButtonsAndText buttonsAndText = new ButtonsAndText();
    private GameData gameData;
    private Button[] operativeButtons = getOperativeButtons();
    private GameLoader gameLoader;
    private BorderPane backgroundPane = new BorderPane();
    private Text userScoreText = new Text();
    private Text dragonScoreText = new Text();
    private Text diffText = new Text();
    private Sounds sounds = new Sounds();

    public Game(GameData gameData, Scene menuScene, GameRanking gameRanking) {
        sounds.playIntro();
        this.gameRanking=gameRanking;
        this.gameData = gameData;
        this.engine = new Engine(gameData.getGameDifficult());
        this.menuScene = menuScene;
        this.gameLoader = new GameLoader( menuScene, backgroundScene, this);
    }

    public void game(Stage primaryStage) {
        this.matchesValue = GameStatics.DEFAULT_MATCHES_VALUE;
        engine.setGameDifficult(gameData.getGameDifficult());
        setGameScene();
        this.primaryStage = primaryStage;
        primaryStage.setScene(gameScene);
        primaryStage.show();

    }

    public void gameLoader(Stage primaryStage) {
        setGameScene();
        gameLoader.start(primaryStage);
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
        final String baseText = GameStatics.DRAGON_NAME + " się zastanawia";
        Text text = new Text(baseText);
        text.setFont(Font.font(null, FontWeight.NORMAL, FontPosture.REGULAR, 30));
        VBox vBox = new VBox();
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.2));
        pauseTransition.setOnFinished(param -> {
            if (text.getText().length() > baseText.length() + 3) {
                text.setText(baseText);
            } else {
                text.setText(text.getText() + ".");
            }
            pauseTransition.play();
        });
        vBox.getChildren().addAll(text);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, GameStatics.SCENE_WIDTH / 3f, GameStatics.SCENE_HEIGHT / 7f, Color.WHITE);
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
            gameRanking.addUser(new UserScore(GameStatics.USER_NAME, gameData.getUserScore(), gameData.getDragonScore(), gameData.getGameDifficult()));
            winnerText.setText("Brawo, wygrałeś z wynikiem " + gameData.getUserScore() + "-" + gameData.getDragonScore());
            gameData.setStartOfGame();
        }

        HBox hBox = getGameButtons();
        borderPane.setBottom(hBox);
        borderPane.setCenter(textPane);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    private void setGameScene() {
        setBackgroundPane();
        borderPane.setTop(backgroundPane);
        borderPane.setMaxSize(GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT);
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
        Button exit= buttonsAndText.newButton("Exit",150,40);
        exit.setOnAction(p->{
            primaryStage.setScene(menuScene);
            primaryStage.show();
        });
        return new Button[]{one, two, thre,exit};
    }

    private void userTurn(int value) {
        substractMatchesValue(value);
        setMatchesValueView();
        sounds.playGamerVoice();
        if (matchesValue == 0) {
            gameData.setUserScore(gameData.getUserScore() + 1);
            setWinner(GameStatics.USER_NAME);
        }
    }

    private void dragonTurn() {
        unblockButtons(true);
        Stage stage = getStatement();
        stage.show();
        double time = random.nextInt(10) / 10f;
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(time + 0.5));
        pauseTransition.setOnFinished(event -> {
            substractMatchesValue(engine.dragonTurn(matchesValue));
            setMatchesValueView();
            sounds.playDragonVoice();
            if (matchesValue == 0) {
                stage.close();
                gameData.setDragonScore(gameData.getDragonScore() + 1);
                setWinner(GameStatics.DRAGON_NAME);
            }
            stage.close();
            unblockButtons(false);
        });
        pauseTransition.play();
    }

    private void unblockButtons(boolean option) {
        for (Button operativeButton : operativeButtons) {
            operativeButton.setDisable(option);
        }
    }

    private void setNormalColorOfButtons() {
        for (Button operativeButton : operativeButtons)
            operativeButton.setTextFill(Color.BLACK);
    }

    private HBox getGameButtons() {
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
            gameLoader.addGameData(new GameData(gameData.getUserScore(), gameData.getDragonScore(), gameData.getGameDifficult()));
            gameLoader.saveGameData();
            gameData.setStartOfGame();
            primaryStage.setScene(menuScene);
            primaryStage.show();

        });

        HBox hBox = new HBox();
        hBox.setSpacing(50);
        hBox.getChildren().addAll(menu, restart, saveAndExit);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: \t#808080;");
        return hBox;
    }

    private void setBackgroundPane() {
        backgroundPane.setBackground(backgroundScene.getBackgroundForGame());
        userScoreText.setText((gameData.getUserScore() + ""));
        userScoreText.setFill(Color.BLUE);
        userScoreText.setFont(Font.font(50));
        dragonScoreText.setText(gameData.getDragonScore() + "");
        dragonScoreText.setFill(Color.RED);
        dragonScoreText.setFont(Font.font(50));
        diffText.setText("Difficult " + gameData.getGameDifficult().getName());
        diffText.setFont(Font.font(null, FontWeight.BLACK, FontPosture.REGULAR, 50));
        diffText.setFill(Color.BLACK);
        BorderPane.setAlignment(diffText, Pos.TOP_CENTER);
        backgroundPane.setLeft(userScoreText);
        backgroundPane.setRight(dragonScoreText);
        backgroundPane.setTop(diffText);
        backgroundPane.setPrefSize(backgroundScene.getImageback().getWidth(), backgroundScene.getImageback().getHeight());
    }

    public void setGameData(int userScore, int dragonScore, GameDifficult gameDifficult) {
        this.gameData.setUserScore(userScore);
        this.gameData.setDragonScore(dragonScore);
        this.gameData.setGameDifficult(gameDifficult);
    }
}

