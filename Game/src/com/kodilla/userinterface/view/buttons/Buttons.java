package com.kodilla.userinterface.view.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Buttons {

    public HBox addHBoxOfButtons(Button[] buttons) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 15, 0, 15));
        hbox.setSpacing(100);
        hbox.setStyle("-fx-background-color: \t#808080;");

        for (Button button : buttons) {
            hbox.getChildren().add(button);
        }

        return hbox;
    }

    public Button newButton(String text) {
        Button button = new Button();
        button.setPrefSize(200, 20);
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setText(text);
        return button;
    }


}
