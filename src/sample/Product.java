package sample;

import javafx.collections.ObservableList;

public class Product {
    //Variable Declaration
    private ObservableList<Part> associatedParts;
    private int id;
    private int stock;
    private int max;
    private int min;
    private double price;
    private String name;

    public Product(ObservableList<Part> allProducts, int id, String name, double price, int stock, int min, int max) {

        this.id = id;
        this.min = min;
        this.max = max;
        this.stock = stock;
        this.price = price;
        this.name = name;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void addAssociatedPart(ObservableList<Part> part) {
        this.associatedParts = part;
    }

    public Part deleteAssociatedPart(Part selectedAssociatedPart) {
        return selectedAssociatedPart;
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
}
