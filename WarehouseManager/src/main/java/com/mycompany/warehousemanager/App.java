package com.mycompany.warehousemanager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    private TableView<Item> table;
    private ObservableList<Item> itemList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Input Fields
        TextField nameField = new TextField();
        nameField.setPromptText("Item Name");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        TextField reorderField = new TextField();
        reorderField.setPromptText("Reorder Level");

        Button addButton = new Button("Add Item");

        // Table Setup
        table = new TableView<>();
        itemList = FXCollections.observableArrayList();

        TableColumn<Item, String> nameCol = new TableColumn<>("Item Name");
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Item, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(data -> data.getValue().quantityProperty().asObject());

        TableColumn<Item, Integer> reorderCol = new TableColumn<>("Reorder Level");
        reorderCol.setCellValueFactory(data -> data.getValue().reorderLevelProperty().asObject());

        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> data.getValue().statusProperty());

        table.getColumns().addAll(nameCol, qtyCol, reorderCol, statusCol);
        table.setItems(itemList);

        // Button Action
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                int reorderLevel = Integer.parseInt(reorderField.getText());

                String status = (quantity <= reorderLevel) ? "⚠ Low Stock" : "OK";

                itemList.add(new Item(name, quantity, reorderLevel, status));

                nameField.clear();
                quantityField.clear();
                reorderField.clear();

            } catch (Exception ex) {
                showAlert("Please enter valid data!");
            }
        });

        // Layout
        HBox inputBox = new HBox(10, nameField, quantityField, reorderField, addButton);
        inputBox.setPadding(new Insets(10));

        VBox root = new VBox(10, inputBox, table);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 700, 400);
        stage.setTitle("Warehouse Inventory System");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}