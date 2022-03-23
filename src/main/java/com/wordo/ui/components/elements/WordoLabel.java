package com.wordo.ui.components.elements;

import javafx.scene.control.Label;

public class WordoLabel implements Element {

    private Label label;
    private String lblText;

    public WordoLabel(String text){
        this.lblText = text;
        label = new Label(text);
    }

    /**
     * Getter for the label.
     * @return
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Setter for the label text.
     * @param lblText
     */
    public void setLblText(String lblText) {
        this.lblText = lblText;
        this.label.setText(lblText);
    }

    /**
     * Clone for the label.
     * @return
     */
    @Override
    public Element clone() { return new WordoLabel(this.lblText); }
}
