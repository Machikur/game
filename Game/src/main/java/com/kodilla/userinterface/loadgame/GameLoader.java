package com.kodilla.userinterface.loadgame;

import com.kodilla.datahandler.DataHandler;
import com.kodilla.datahandler.GameStatics;
import com.kodilla.engine.GameData;
import com.kodilla.userinterface.background.BackgroundScene;
import com.kodilla.userinterface.buttons.ButtonsAndText;
import com.kodilla.userinterface.game.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameLoader {
    private List<GameData> list;
    private ButtonsAndText buttonsAndText = new ButtonsAndText();
    private VBox vBox;
    private HBox hBox;
    private GameData chosenGameData = null;
    private Stage primaryStage;
    private Scene scene;
    private Scene menuScene;
    private Game game;
    private BackgroundScene backgroundScene;
    private Button selectedButton;
    private DataHandler dataHandler = new DataHandler();

    public GameLoader(Scene menuScene, BackgroundScene backgroundScene, Game game) {
        this.backgroundScene = backgroundScene;
        this.menuScene = menuScene;
        this.game = game;
        this.list = (List<GameData>) dataHandler.loadFile(GameStatics.GAMEDATA_PATH);
        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }

    }

    public void start(Stage primaryStage) {
        setScene();
        this.primaryStage = primaryStage;

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox buildVBoxButtons() {
        VBox vBox = new VBox();
        if (Objects.nonNull(list) && list.size() != 0) {
            ArrayList<Button> buttonsList = new ArrayList<>();
            vBox.setSpacing(50f / list.size());
            for (int i = 0; i < list.size(); i++) {
                final int index = i;
                Button button = buttonsAndText.newButton(i + 1 + ". " + list.get(index), 350, 60);
                buttonsList.add(button);
                button.setOnAction(p -> {
                    if (Objects.nonNull(selectedButton)) {
                        selectedButton.setStyle(GameStatics.DEFAULTBUTTONSTYLE);
                    }
                    chosenGameData = list.get(index);
                    selectedButton = button;
                    button.setStyle("-fx-background-color: chartreuse");
                });
            }
            vBox.getChildren().addAll(buttonsList);
        } else {
            Text text = new Text("Brak zapisanych gier");
            buttonsAndText.getTextEffect(text);
            vBox.getChildren().add(text);
        }
        return vBox;
    }

    private HBox buildHBoxButtons() {
        HBox hBox = new HBox();
        Button exit = buttonsAndText.newButton("Exit", 200, 20);
        exit.setOnAction(p -> {
            primaryStage.setScene(menuScene);
            primaryStage.show();
        });
        hBox.getChildren().add(exit);

        if (Objects.nonNull(list) && list.size() != 0) {
            hBox.setSpacing(100);
            Button loadGame = buttonsAndText.newButton("Load game", 200, 20);
            loadGame.setOnAction(p -> {
                if (Objects.nonNull(chosenGameData)) {
                    game.setGameData(chosenGameData.getUserScore(), chosenGameData.getDragonScore(), chosenGameData.getGameDifficult());
                    game.game(primaryStage);
                }
            });

            Button deleteSave = buttonsAndText.newButton("Delete", 200, 20);
            deleteSave.setOnAction(p -> {
                if (chosenGameData != null) {
                    list.remove(chosenGameData);
                    chosenGameData = null;
                    start(primaryStage);
                }
            });
            hBox.getChildren().addAll(loadGame, deleteSave);
        }
        return hBox;
    }

    public void setScene() {
        vBox = buildVBoxButtons();
        hBox = buildHBoxButtons();
        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        borderPane.setBottom(hBox);

        borderPane.setBackground(backgroundScene.getBackground());
        scene = new Scene(borderPane, GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT, Color.WHITE);
    }

    public void addGameData(GameData gameData) {
        if (list.size() < 10) {
            list.add(gameData);
        }

        list.sort(GameData::compareTo);
    }

    public void saveGameData() {
        dataHandler.saveFile(list, GameStatics.GAMEDATA_PATH);
    }
}
