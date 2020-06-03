package com.kodilla.userinterface.view.rules;

import com.kodilla.userinterface.BoardUI;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Rules {

    private Stage stage;


    public Rules() {

        double nodeWidth = BoardUI.SCENE_WIDTH / 1.4f;
        double nodeHeight = BoardUI.SCENE_HEIGHT / 2f;

        BorderPane border = new BorderPane();
        Text text = new Text("Znalazłeś sie u bram zamku którego strzegą smoki\n Aby się dostać musisz je pokonać\n" +
                " Chcą one abyście na zmiane ciągneli po jednej,\n dwóch lub trzech zapałkach.\n Wygrywa ten który pociągnie ostatni\n Powodzenia");
        text.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        border.setCenter(text);

        Scene scene = new Scene(border, nodeWidth, nodeHeight, Color.BLACK);
        Stage startStage = new Stage();
        startStage.setScene(scene);

        border.setBottom(getDefinedHBox());

        this.stage = startStage;
    }

    private HBox getDefinedHBox(){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 270, 0, 300));
        hBox.getChildren().add(getButton());
        return hBox;
    }

    private Button getButton() {
        Button button = new Button();
        button.setText("Kontynnuj");
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setMinSize(50, 20);
        button.setOnAction(param ->
                this.stage.close());

        return button;

    }

    public Stage getStage() {
        return stage;
    }


}
