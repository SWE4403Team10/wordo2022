package com.wordo.ui.components.elements;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class WordoRadioButton implements Element {

    private ToggleGroup toggleGroup;
    private String rbText;
    private RadioButton radioButton;

    public WordoRadioButton(ToggleGroup toggleGroup, String rbText){
        this.toggleGroup = toggleGroup;
        this.rbText = rbText;
        this.radioButton = new RadioButton(rbText);
        this.radioButton.setToggleGroup(toggleGroup);
    }

    /**
     * Getter for the radio button.
     * @return
     */
    public RadioButton getRadioButton() {
        return radioButton;
    }

    /**
     * Setter for the radio button text.
     * @param rbText
     */
    public void setRbText(String rbText) {
        this.rbText = rbText;
        this.radioButton.setText(rbText);
    }

    /**
     * Clone for the radio button.
     * @return
     */
    @Override
    public Element clone() { return new WordoRadioButton(this.toggleGroup, this.rbText); }
}
