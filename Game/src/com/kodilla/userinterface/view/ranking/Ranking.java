package com.kodilla.userinterface.view.ranking;

import com.kodilla.datahandler.DataHandler;
import com.kodilla.userinterface.Game;
import com.kodilla.userinterface.view.background.BackgroundScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ranking {


    private Stage stage;
    private Scene menuScene;
    private File file = new File("Game/resources/ranking.txt");

    private List<UserScore> bestUsers = new ArrayList<>();

    public Ranking() {
        try {
            DataHandler dataHandler = new DataHandler();
            bestUsers = (ArrayList<UserScore>) dataHandler.loadFile(file);
        } catch (Exception s) {
            s.printStackTrace();
        }
    }

    public void getRanking(Stage primaryStage, Scene menuScene) {
        this.stage = primaryStage;
        this.menuScene = menuScene;

        BackgroundScene backgroundScene = new BackgroundScene();


        BorderPane border = new BorderPane();
        border.setBackground(backgroundScene.getBackground());
        Text text = bestUserToString();
        text.setTextAlignment(TextAlignment.CENTER);
        border.setCenter(text);
        HBox hBox = getDefinedHBox();
        hBox.setAlignment(Pos.CENTER);
        border.setBottom(hBox);


        Scene scene = new Scene(border, Game.SCENE_WIDTH, Game.SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addUser(UserScore user) {
        if (bestUsers.size() < 10 || bestUsers.get(9).getPoints() < user.getPoints()) {
            bestUsers.add(user);
        }
        bestUsers = bestUsers.stream()
                .limit(10)
                .sorted(Comparator.comparingInt(UserScore::getPoints))
                .collect(Collectors.toList());

    }

    private Text bestUserToString() {
        Text text = new Text();
        if (bestUsers.size() == 0) {
            text.setText("Brak pozycji w rankingu");
        } else {
            String records = "";
            for (int i = 0; i < bestUsers.size(); i++) {
                records += (i + 1 + " " + bestUsers.get(i) + "\n");
            }
            text.setText(records);
        }
        DropShadow ds = new DropShadow();
        text.setFont(Font.font(null, FontWeight.BOLD, 30));
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setCache(true);
        text.setX(10.0f);
        text.setY(270.0f);
        text.setFill(Color.BLUE);
        text.setEffect(ds);
        return text;

    }

    private HBox getDefinedHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 270, 0, 300));
        hBox.getChildren().add(getButton());
        return hBox;
    }

    private Button getButton() {
        Button button = new Button();
        button.setText("Kontynnuj");
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setMinSize(200, 80);
        button.setOnAction(param -> {
            stage.setScene(menuScene);
            stage.show();
        });
        return button;

    }

    public List<UserScore> getBestUsers() {
        return bestUsers;
    }

    public File getFile() {
        return file;
    }
}



