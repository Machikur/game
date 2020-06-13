package com.kodilla.userinterface.view.menu;

import com.kodilla.datahandler.DataHandler;
import com.kodilla.datahandler.Paths;
import com.kodilla.engine.GameData;
import com.kodilla.engine.GameDifficult;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.game.Game;
import com.kodilla.userinterface.view.loadgame.GameLoader;
import com.kodilla.userinterface.view.ranking.Ranking;
import com.kodilla.userinterface.view.rules.Rules;
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
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Menu {
    public static String DEFAULTBUTTONSTYLE = new Button().getStyle();

    private BorderPane borderPane = new BorderPane();
    private Rules rules = new Rules();
    private DataHandler dataHandler = new DataHandler();
    private Game game;
    private BackgroundScene backgroundScene = new BackgroundScene();
    private Scene menuScene=new Scene(borderPane, Game.SCENE_WIDTH, Game.SCENE_HEIGHT, Color.WHITE);
    private Ranking ranking=new Ranking(menuScene,backgroundScene);
    private Button[] difficultButtons = new Button[3];
    private List<GameData> gameDataList=getGameDataList();
    private GameData gameData=new GameData();
    private GameLoader gameLoader;



    public void start(Stage primaryStage) {

        VBox vBox = new VBox(30);
        Button start = newButton("Start new\n   game");
        start.setOnAction(param -> {
            if (Objects.isNull(game)) {
                game = new Game(gameData, ranking, menuScene, gameDataList);
            }
            game.game(primaryStage);
        });

        Button load = newButton("Load game");
        load.setOnAction(param -> {
            if (Objects.isNull(gameLoader)) {
                 gameLoader = new GameLoader(gameDataList, ranking, menuScene, backgroundScene, game);
            }
            gameLoader.Start(primaryStage);
        });


        Button ranking = newButton("Ranking");
        ranking.setOnAction(param ->
                this.ranking.getRanking(primaryStage));

        Button rulesButton = newButton("Show rules");
        rulesButton.setOnAction(param -> rules.getRules(primaryStage, menuScene));

        Button exit = newButton("Exit");
        exit.setOnAction(p -> {
            dataHandler.saveFile(this.ranking.getBestUsers(), Paths.RANKING_PATH);
            dataHandler.saveFile(gameDataList, Paths.GAMEDATA_PATH);
            primaryStage.close();

        });

        vBox.getChildren().addAll(start, load, ranking, rulesButton, exit);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        borderPane.setBackground(backgroundScene.getBackground());
        borderPane.setBottom(difficultyButtons());

        primaryStage.setScene(menuScene);
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
        int counter = 0;

        for (GameDifficult gameDif : GameDifficult.values()) {
            Button button = newButton(gameDif.getName());
            button.setOnAction(p -> {
                setDifficulty(gameDif);
                setButtonsColor();
                button.setStyle("-fx-background-color: chartreuse");
            });

            button.setPrefSize(200, 40);
            hBox.getChildren().add(button);
            difficultButtons[counter] = button;
            counter++;
        }

        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }


    private void setDifficulty(GameDifficult gameDifficult) {
        gameData.setGameDifficult(gameDifficult);
    }

    private void setButtonsColor() {
        for (Button button : difficultButtons) {
            button.setStyle(DEFAULTBUTTONSTYLE);
        }
    }

    private List<GameData> getGameDataList(){
        List<GameData> gameDataList=(List<GameData>) dataHandler.loadFile(Paths.GAMEDATA_PATH);
        if (Objects.isNull(gameDataList)){
            return new ArrayList<>();
        }
        else return gameDataList;
    }

}
