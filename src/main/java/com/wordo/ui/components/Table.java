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

    private final TableView<Word> tv;
    private int numOfColumns;
    private final ObservableList<Word> data = FXCollections.observableArrayList();
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
            Word word = new Word();
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
    private int currentRow;

    public void createColumnsCallbacks(boolean lastColumn) {
        TableColumn<Word, Void> column = new TableColumn<>();
        Callback<TableColumn<Word, Void>, TableCell<Word, Void>> factory = new Callback<>() {

            @Override
            public TableCell<Word, Void> call(TableColumn<Word, Void> param) {
                return new TableCell<Word, Void>() {

                    private int columnIndex = param.getTableView().getColumns().indexOf(param);
                    private int rowIndex;
                    private TextField selectedTextField = null;
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
                            if(t.length() == 1) {
                                letters[columnIndex] = t;
//                                if(columnIndex != numOfColumns-1){
                                    Platform.runLater(() -> robot.keyPress(KeyCode.TAB));
//                                } else {
//
//                                }
                            }
                        });

                        inputTF.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
                            inputTF.setId(columnIndex + " " + rowIndex);
                            selectedTextField = inputTF;
                            if(t1){
                                if(currentRow < (selectedTextField.getId().charAt(2) - '0')) {
                                    System.out.println(currentRow);
                                    System.out.println(selectedTextField.getId().charAt(2));
                                    currentRow = selectedTextField.getId().charAt(2) - '0';
                                }
                                if(!inputTF.getText().equals("")){
                                    if((selectedTextField.getId().charAt(2) - '0') < currentRow){
                                        Platform.runLater(() -> robot.keyPress(KeyCode.TAB));
                                    }
                                }
                                System.out.println("Selected Row " + selectedTextField.getId().charAt(2));
                                System.out.println("Selected Row & Column " + selectedTextField.getId());
                                System.out.println("Curr Row Index " + currentRow);
                            }
                        });
                    }

                    @Override
                    public void updateIndex(int i) {
                        super.updateIndex(i);
                        rowIndex = i;
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


