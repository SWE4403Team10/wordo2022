package com.wordo.ui.components;

import com.wordo.ui.ColorStyle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {

    private final TableView<Word> tv;
    private int numOfColumns;
    private final ObservableList<Word> data = FXCollections.observableArrayList();
    private static String[] letters;
    private int guessNumber = 0;
    private final List<Rectangle> tableCells = new ArrayList<Rectangle>();

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
        TableColumn<Word, String> column = new TableColumn<>();
        Callback<TableColumn<Word, String>, TableCell<Word, String>> factory = new Callback<>() {

            @Override
            public TableCell<Word, String> call(TableColumn<Word, String> param) {
                TableCell<Word, String> cell = new TableCell<Word, String>() {

                    private int columnIndex = param.getTableView().getColumns().indexOf(param);
                    private int rowIndex;
                    private TextField selectedTextField = null;
                    private TextField inputTF = new TextField();
                    private Rectangle rec = new Rectangle(50.0, 10.0, 50.0, 10.0);
                    private VBox vboxTemp = new VBox(inputTF, rec);

                    {
                        tv.getFocusModel().focus(0, tv.getColumns().get(0));
                        tv.requestFocus();

                        inputTF.setAlignment(Pos.CENTER);
                        inputTF.setMaxHeight(25);
                        inputTF.setMinHeight(25);
                        inputTF.setMaxWidth(25);
                        inputTF.setMinWidth(25);
                        inputTF.setStyle("-fx-background-color: transparent;");

                        rec.setFill(Color.TRANSPARENT);
                        rec.setId(columnIndex + " " + rowIndex);

                        vboxTemp.setAlignment(Pos.CENTER);
                        tableCells.add(rec);
                        int[] temp = {0,1,2,1};

                        Robot robot = new Robot();
                        if(numOfColumns-1 == columnIndex){
                             inputTF.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                 @Override
                                 public void handle(KeyEvent keyEvent) {
                                     if(keyEvent.getCode() == KeyCode.ENTER){
                                         for(int i = 0; i < tableCells.size(); i++){
                                             System.out.println(tableCells.get(i));
                                             System.out.println(tableCells.get(i).getId());
                                             if(temp.length > i-4){
                                                 changeColors(tableCells.get(i), temp[1]);
                                             }
                                                 System.out.println("bruh");


                                         }
                                         Platform.runLater(() -> robot.keyPress(KeyCode.TAB));
                                     }
                                 }
                             });
                        }

                        inputTF.textProperty().addListener((obs, s, t) -> {
                            if(columnIndex == numOfColumns-1){
                                if(t.length() >= 1){
                                    inputTF.setText(inputTF.getText().substring(0,1));
                                }
                            } else {
                                letters[columnIndex] = t;
                                Platform.runLater(() -> robot.keyPress(KeyCode.TAB));
                            }
                        });

                        inputTF.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
                            inputTF.setId(columnIndex + " " + rowIndex);
                            selectedTextField = inputTF;
                            if(t1){
                                if(currentRow < (selectedTextField.getId().charAt(2) - '0')) {
                                    currentRow = selectedTextField.getId().charAt(2) - '0';
                                }
                                if(!inputTF.getText().equals("")){
                                    if((selectedTextField.getId().charAt(2) - '0') < currentRow){
                                        Platform.runLater(() -> robot.keyPress(KeyCode.TAB));
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void updateIndex(int i) {
                        super.updateIndex(i);
                        rowIndex = i;
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if(empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(vboxTemp);
                        }
                    }

                };
                return cell;
            }

        };
        column.setStyle("-fx-alignment: CENTER");
        column.setCellFactory(factory);
        column.setSortable(false);
        tv.getColumns().add(column);

    }

    public void changeColors(Rectangle rectangle, int colorValue){
        switch (colorValue) {
            case 1 -> rectangle.setFill(Color.YELLOW);
            case 2 -> rectangle.setFill(Color.GREEN);
            default -> rectangle.setFill(Color.LIGHTGREY);
        }
    }
}


