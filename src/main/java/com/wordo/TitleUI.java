package com.wordo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TitleUI extends Application {
//    TableView<String> table = new TableView<>();
//    ObservableList<String> list = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) throws IOException {


//        TableColumn<String, String> nameColumn = new TableColumn<>("Bruh");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>(""));
//        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

//        table.getColumns().add(nameColumn);
//        list.add("Bruh");
        //

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        createRowsColumnsForGridPane(grid, 9, 7);

        Button btnSettings = new Button("settings");
        btnSettings.setMinSize(50, 25);
        btnSettings.setAlignment(Pos.TOP_LEFT);

        HBox hboxSettings = new HBox(btnSettings);
        hboxSettings.setMaxSize(100, 75);

        grid.add(hboxSettings, 0, 0);

        Label lblTitle = new Label("Wordo");
        lblTitle.setAlignment(Pos.CENTER);

        HBox hboxTitle = new HBox(lblTitle);
        hboxTitle.setAlignment(Pos.CENTER);

        grid.add(hboxTitle, 3, 0);

        Scene scene = new Scene(grid, 450, 375);

        stage.setTitle("Wordo");
        stage.setScene(scene);
        stage.show();
    }

    public static void createRowsColumnsForGridPane(GridPane grid, int rows, int cols) {
        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(row);
        }
        for(int i = 0; i < cols; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(col);
        }
    }

    public static void main() {
        launch();
    }
}