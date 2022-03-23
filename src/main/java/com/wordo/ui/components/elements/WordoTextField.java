package com.wordo.ui.components.elements;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class WordoTextField implements Element {

    private double height;
    private double width;
    private TextField textField;

    public WordoTextField(double width, double height){

        this.textField = new TextField();
        this.textField.setAlignment(Pos.CENTER);
        this.textField.setMaxWidth(width);
        this.textField.setMaxHeight(height);
        this.textField.setMinWidth(width);
        this.textField.setMinHeight(height);
        this.textField.setAlignment(Pos.CENTER);
        this.textField.setStyle("-fx-background-color: transparent; -fx-font-size: 15px;");
        this.height = height;
        this.width = width;

    }

    public void setHeight(double height) {
        this.height = height;
        this.textField.setMaxHeight(height);
    }

    public void setWidth(double width) {
        this.width = width;
        this.textField.setMaxWidth(width);
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public TextField getTextField() {
        return textField;
    }

    @Override
    public Element clone() { return new WordoTextField(this.width, this.height); }
}
