package com.wordo.ui.components;

import com.wordo.gamelogic.GameLogic;
import com.wordo.ui.GameUI;
import com.wordo.ui.components.elements.WordoTextField;
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
import org.w3c.dom.Text;

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
    private final List<TextField> tableTF = new ArrayList<TextField>();
    private GameLogic gameLogic = GameLogic.getInstance();
    private GameUI gameUI = new GameUI();

    private int currentRow;
    private int[] guessResult = {};
    private int minIndex = gameLogic.getDifficultyWordLength();
    private int maxIndex = guessResult.length + minIndex*2;
    private int guessIndex = 0;

    /**
     * Initialization of the Table
     * @param diff
     */
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
        Arrays.fill(letters, "");
    }

    /**
     * A middle method for creating the columns.
     * Creates a specific number of columns based on the difficulty.
     */
    public void createColumns() {
        for (int i = 0; i < numOfColumns - 1; i++) {
            createColumnsCallbacks(false);
        }
        createColumnsCallbacks(true);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * This add temporary data into the table to allow it to be visible.
     * Puts 'word' objects into the rows.
     */
    public void addData() {
        for (int i = 0; i < 6; i++) {
            Word word = new Word();
            data.add(word);
        }
        tv.setItems(data);

    }

    /**
     * A getter for the Tableview
     * @return tv : Tableview
     */
    public TableView getTable() {
        return tv;
    }

    /**
     * A getter for the guess made.
     * Will return the guess as a string.
     * @return guess : String
     */
    public static String getGuess() {
        String guess = "";
        for (String s : letters) {
            guess += s;
        }
        return guess;
    }

    /**
     * Adjust the size of the rows.
     * @param size
     */
    public void setNumberOfVisibleCells(int size) {
        tv.setFixedCellSize(size);
        tv.prefHeightProperty().bind(Bindings.size(tv.getItems()).multiply(tv.getFixedCellSize()).add(0));
    }

    /**
     * This method creates the columns in the table.
     * It also has two listeners:
     *  - Text Listener : Listens for a single input and move on when a single input has arrived.
     *  - Focused Listener : Listens for when a textfield is focused and move on until a textfield doesn't have text.
     * @param lastColumn
     */
    public void createColumnsCallbacks(boolean lastColumn) {
        TableColumn<Word, String> column = new TableColumn<>();
        Callback<TableColumn<Word, String>, TableCell<Word, String>> factory = new Callback<>() {

            @Override
            public TableCell<Word, String> call(TableColumn<Word, String> param) {
                TableCell<Word, String> cell = new TableCell<Word, String>() {

                    private int columnIndex = param.getTableView().getColumns().indexOf(param);
                    private int rowIndex;
                    private TextField selectedTextField = null;
                    private Rectangle rec = new Rectangle(50.0, 10.0, 50.0, 10.0);
                    private VBox vboxTemp;
                    private WordoTextField charInput;

                    {

                        charInput = new WordoTextField(35, 27);

                        vboxTemp = new VBox(charInput.getTextField(), rec);

                        rec.setFill(Color.TRANSPARENT);
                        rec.setId(columnIndex + " " + rowIndex);

                        vboxTemp.setAlignment(Pos.CENTER);
                        tableCells.add(rec);
                        tableTF.add(charInput.getTextField());

                        Robot robot = new Robot();
                             charInput.getTextField().setOnKeyPressed(new EventHandler<KeyEvent>() { // When a key is pressed, do the following.
                                 @Override
                                 public void handle(KeyEvent keyEvent) { // How it will handle the key press.
                                     if(lastColumn){
                                         if(keyEvent.getCode() == KeyCode.ENTER){ // If the key pressed is ENTER.
                                             guessResult = gameLogic.checkGuess(getGuess());
                                             for(int i = minIndex; i < tableCells.size()+ minIndex; i++){
                                                 if(maxIndex > i){ // Only allows the current row (guess) to be coloured.
                                                     changeColors(tableCells.get(i), guessResult[guessIndex]);
                                                     guessIndex++;
                                                 }
                                             }
                                             if(gameLogic.isCorrect()){ // A check to see if the guess is correct.
                                                 gameUI.showWord(true);
                                                 tv.setDisable(true);
                                             } else if(!gameLogic.getNumGuesses()){
                                                 gameUI.showWord(false);
                                             }
                                             guessIndex = 0;
                                             minIndex = maxIndex;
                                             maxIndex += gameLogic.getDifficultyWordLength();
                                             Platform.runLater(() -> robot.keyPress(KeyCode.TAB));
                                         }
                                     }
                                     if(keyEvent.getCode() == KeyCode.BACK_SPACE){ // If the key pressed is BACK_SPACE.
                                         tableTF.get(tableTF.indexOf(selectedTextField)-1).requestFocus();
                                     }
                                 }
                             });
                        }

                        charInput.getTextField().textProperty().addListener((obs, s, t) -> { // Text Input Listener
                            if(lastColumn){
                                if(t.length() >= 1){ // If the length of the input is >= 1.
                                    letters[columnIndex] = t;
                                    charInput.getTextField().setText(charInput.getTextField().getText().substring(0,1));
                                }
                            } else {
                                letters[columnIndex] = t;
                                Platform.runLater(() -> robot.keyPress(KeyCode.TAB));
                            }
                        });

                        charInput.getTextField().focusedProperty().addListener((observableValue, aBoolean, t1) -> { // Listens for when a textfield is focus upon.
                            charInput.getTextField().setId(columnIndex + " " + rowIndex);
                            selectedTextField = charInput.getTextField();
                            if(t1){
                                if(currentRow < (selectedTextField.getId().charAt(2) - '0')) {
                                    currentRow = selectedTextField.getId().charAt(2) - '0'; // Updates to the current guess (row).
                                }
                                if(!charInput.getTextField().getText().equals("")){ // If the focused textfield has a value inside.
                                    if((selectedTextField.getId().charAt(2) - '0') < currentRow){
                                        Platform.runLater(() -> robot.keyPress(KeyCode.TAB));
                                    }
                                    if((selectedTextField.getId().charAt(2) - '0') == currentRow) {
                                        selectedTextField.setText("");
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
        column.setStyle("-fx-alignment: CENTER; -fx-background-color: #FFFFFF;");
        column.setCellFactory(factory);
        column.setSortable(false);
        tv.getColumns().add(column);

    }

    /**
     * Changes the color of the cell according to the elements returned from the guess.
     * @param rectangle
     * @param colorValue
     */
    public void changeColors(Rectangle rectangle, int colorValue){
        switch (colorValue) {
            case 1 -> rectangle.setFill(Color.YELLOW);
            case 2 -> rectangle.setFill(Color.GREEN);
            default -> rectangle.setFill(Color.LIGHTGREY);
        }
    }

    /**
     * Setter for the GameUI.
     * @param gameUI
     */
    public void setGameUI(GameUI gameUI){ this.gameUI = gameUI; }

}


