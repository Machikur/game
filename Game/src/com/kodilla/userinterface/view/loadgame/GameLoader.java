package com.kodilla.userinterface.view.loadgame;

import com.kodilla.datahandler.DataHandler;
import com.kodilla.datahandler.GameStatics;
import com.kodilla.engine.GameData;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.buttons.ButtonsAndText;
import com.kodilla.userinterface.view.game.Game;
import com.kodilla.userinterface.view.ranking.Ranking;
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
    private Ranking ranking;
    private Game game;
    private BackgroundScene backgroundScene;
    private Button selectedButton;

    public GameLoader(Ranking ranking, Scene menuScene, BackgroundScene backgroundScene, Game game) {
        this.backgroundScene = backgroundScene;
        this.menuScene = menuScene;
        this.ranking = ranking;
        this.game = game;
        DataHandler dataHandler = new DataHandler();
        this.list = (List<GameData>) dataHandler.loadFile(GameStatics.GAMEDATA_PATH);
        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }

    }

    public void Start(Stage primaryStage) {
        setScene();
        this.primaryStage = primaryStage;

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox buildVBoxButtons() {
        VBox vBox = new VBox();
        if (Objects.nonNull(list) && list.size() != 0) {
            ArrayList<Button> buttonsList = new ArrayList<>();
            vBox.setSpacing(50 / list.size());
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
                    game = new Game(chosenGameData, ranking, menuScene, this);
                    game.game(primaryStage);
                }
            });

            Button deleteSave = buttonsAndText.newButton("Delete", 200, 20);
            deleteSave.setOnAction(p -> {
                if (chosenGameData != null) {
                    list.remove(chosenGameData);
                    chosenGameData = null;
                    Start(primaryStage);
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
        scene = new Scene(borderPane, Game.SCENE_WIDTH, Game.SCENE_HEIGHT, Color.WHITE);
    }

    public List<GameData> getList() {
        return list;
    }

    public void addGameData(GameData gameData) {
        if (list.size() < 10 && !list.contains(gameData)) {
            list.add(gameData);
        }

        list.sort(GameData::compareTo);
    }
}
