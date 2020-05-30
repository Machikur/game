package com.kodilla.view.winner;

import com.kodilla.userinterface.BoardUI;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Winner {

    public void setWinner(String winner) {
        double nodeWidth = BoardUI.SCENE_WIDTH / 2f;
        double nodeHeight = BoardUI.SCENE_HEIGHT / 4f;

        BorderPane border = new BorderPane();
        Text text = new Text("Wygrał " + winner);
        text.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 50));
        border.setCenter(text);

        Stage stage = new Stage();
        Scene scene = new Scene(border, nodeWidth, nodeHeight, Color.BLACK);
        stage.setScene(scene);
        stage.show();


    }
}
