package com.mycompany.warehousemanager;

import javafx.beans.property.*;

public class Item {

    private StringProperty name;
    private IntegerProperty quantity;
    private IntegerProperty reorderLevel;
    private StringProperty status;

    public Item(String name, int quantity, int reorderLevel, String status) {
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.reorderLevel = new SimpleIntegerProperty(reorderLevel);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public IntegerProperty reorderLevelProperty() {
        return reorderLevel;
    }

    public StringProperty statusProperty() {
        return status;
    }
}