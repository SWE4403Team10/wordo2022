package com.wordo.ui.components;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import org.w3c.dom.Text;

public class Table {

    private final TableView<WordEasy> tv;
    private int numOfColumns;
    private final ObservableList<WordEasy> data = FXCollections.observableArrayList();
    private final String[] letters = new String[5];
    private int guessNumber = 0;

    public Table() {
        tv = new TableView<>();
        tv.setEditable(true);
    }

    public void createColumns(int numOfColumns){
        this.numOfColumns = numOfColumns;

        for(int i = 0; i < numOfColumns-1; i++) {
            TableColumn<WordEasy, String> column = new TableColumn<>();

            column.setCellFactory(TextFieldTableCell.forTableColumn());
            int letterIndex = i;
            column.setOnEditCommit(e -> {
                String letter = e.getNewValue();
                letters[letterIndex] = letter;
                if(letter.length() == 1) {
                    Platform.runLater(() -> tv.edit(guessNumber, tv.getColumns().get(letterIndex+1)));
                } else {
                    Platform.runLater(() -> tv.edit(guessNumber, column));
                }
            });
            tv.getColumns().add(column);
        }

        TableColumn<WordEasy, String> column5 = new TableColumn<>();
        column5.setCellFactory(TextFieldTableCell.forTableColumn());
        column5.setOnEditCommit(e -> {
            String letter = e.getNewValue();
            letters[letters.length-1] = letter;
            guessNumber++;
            Platform.runLater(() -> tv.edit(guessNumber, tv.getColumns().get(0)));
            for (String s : letters) {
                System.out.print(s);
            }

        });
        tv.getColumns().add(column5);

        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void addData() {
        for(int i = 0; i < numOfColumns; i++){
            WordEasy letterInWord = new WordEasy();
            data.add(letterInWord);
        }
        tv.setItems(data);

    }

    public TableView getTable() {
        return tv;
    }

    public String[] getGuess() {
        return letters;
    }

    public void changeColour() {

    }


}

