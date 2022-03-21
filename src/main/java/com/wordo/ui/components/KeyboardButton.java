package com.wordo.ui.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class KeyboardButton {
    private Button button;

    public Button getButton(){
        return button;
    }

    // Constructor
    public KeyboardButton(char c){
        button = new Button(""+c);
        button.setAlignment(Pos.BOTTOM_CENTER);
        button.setPrefHeight(50);
        button.setPrefWidth(33);
        button.setStyle("-fx-background-color: #A9A9A9; -fx-text-fill: #ffffff; ");
        button.setStyle("-fx-background-color: #A9A9A9; -fx-text-fill: #ffffff; ");

        //TODO call controller class to update table
        button.setOnAction(actionEvent -> {
            System.out.println(button.getText());
            this.setState("GRAY");
        });
    }

    public void setState(String color){
        switch(color){
            case "GREEN":
                button.setStyle("-fx-background-color: #008000; -fx-text-fill: #ffffff; ");
                break;
            case "YELLOW":
                button.setStyle("-fx-background-color: #FFD700; -fx-text-fill: #ffffff; ");
                break;
            case "GRAY":
                button.setStyle("-fx-background-color: #3C3C3C; -fx-text-fill: #ffffff; ");
                //button.setDisable(true); Allow users to click the button even if letter is not in word
                break;
            default:
                button.setStyle("-fx-background-color: #A9A9A9; -fx-text-fill: #ffffff; ");
        }
    }
}
