package com.wordo.ui.components;

import com.wordo.ui.ColorStyle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.robot.Robot;
import javafx.util.Callback;
import org.controlsfx.control.PropertySheet;

import java.util.Arrays;

public class Table {

    private final TableView<WordEasy> tv;
    private int numOfColumns;
    private final ObservableList<WordEasy> data = FXCollections.observableArrayList();
    private static String[] letters;
    private int guessNumber = 0;

    public Table(int diff) {
        tv = new TableView<>();
        letters = new String[diff];
        numOfColumns = diff;
        tv.skinProperty().addListener((a, b, newSkin) -> {
            Pane header = (Pane) tv.lookup("TableHeaderRow");
            header.setMinHeight(0);
            header.setPrefHeight(0);
            header.setMaxHeight(0);
            header.setVisible(false);
        });
        tv.setEditable(true);
        Arrays.fill(letters, "");
        guessNumber = 0;
    }

    public void createColumns() {
        for (int i = 0; i < numOfColumns - 1; i++) {
            createColumnsCallbacks(false);
        }

        createColumnsCallbacks(true);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void addData() {
        for (int i = 0; i < 6; i++) {
            WordEasy word = new WordEasy();
            data.add(word);
        }
        tv.setItems(data);

    }

    public TableView getTable() {
        return tv;
    }

    public static String getGuess() {
        String temp = "";
        for (String s : letters) {
            temp += s;
        }
        return temp;
    }

    public void setNumberOfVisibleCells(int i) {
        tv.setFixedCellSize(i);
        tv.prefHeightProperty().bind(Bindings.size(tv.getItems()).multiply(tv.getFixedCellSize()).add(0));
    }

    public void createColumnsCallbacks(boolean lastColumn) {
        TableColumn<WordEasy, Void> column = new TableColumn<>();
        Callback<TableColumn<WordEasy, Void>, TableCell<WordEasy, Void>> factory = new Callback<>() {

            @Override
            public TableCell<WordEasy, Void> call(TableColumn<WordEasy, Void> param) {
                return new TableCell<WordEasy, Void>() {

                    private int columnIndex = param.getTableView().getColumns().indexOf(param);
                    private TextField inputTF = new TextField();

                    {
                        inputTF.setAlignment(Pos.CENTER);
                        inputTF.setMaxHeight(25);
                        inputTF.setMinHeight(25);
                        inputTF.setMaxWidth(25);
                        inputTF.setMinWidth(25);
                        inputTF.setStyle("-fx-background-color: transparent;");


                        Robot robot = new Robot();
                        inputTF.textProperty().addListener((obs, s, t) -> {
                            if(s.length() == 0) {
                                letters[columnIndex] = t;
//                                System.out.println(getGuess());
                                Platform.runLater(() -> robot.keyPress(KeyCode.TAB));
                            }
                        });
                    }

                    @Override
                    public void updateIndex(int i) {
                        super.updateIndex(i);
                        // select color based on index of row/column
                        if (i >= 0) {
                            if(i == 1 && columnIndex == 0){
//                                this.setStyle(ColorStyle.green);
                            }
                            // select color repeating the color, if we run out of colors
                        }
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        // assign item's toString value as text
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(inputTF);
                        }
                    }

                };
            }

        };
        column.setStyle("-fx-alignment: CENTER");
        column.setCellFactory(factory);
        column.setSortable(false);
        tv.getColumns().add(column);

    }
}


