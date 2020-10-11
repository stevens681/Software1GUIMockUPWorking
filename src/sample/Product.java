package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This is the product
 * @author Fernando Rosa
 */
public class Product {

    //Variable Declaration
    private static ObservableList<Part> associatedParts= FXCollections.observableArrayList();
    private int id;
    private int stock;
    private int max;
    private int min;
    private double price;
    private String name;

    /**
     * This is a product and it's fields
     * @param associatedParts If is associated to a part
     * @param id The product id
     * @param name The product name
     * @param price The product price
     * @param stock The product stock level
     * @param min The product minimum stock level
     * @param max The product maximum stock level
     */
    public Product(ObservableList<Part> associatedParts, int id, String name, double price, int stock, int min, int max) {

        this.id = id;
        this.min = min;
        this.max = max;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.associatedParts.addAll(associatedParts);
    }

    /**
     * @param id The id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price The price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param stock The stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @param min The minimum to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @param max The maximum to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @return The stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @return The max
     */
    public int getMax() {
        return max;
    }

    /**
     * @return The min
     */
    public int getMin() {
        return min;
    }

    /**
     * @return The price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * This adds an associated part
     * @param part The part to be set
     */
    public static void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This disassociates a part
     * @param selectedAssociatedPart The selected part to be deleted
     * @return The part to be deleted
     */
    public static boolean deleteAssociatedPart(Part selectedAssociatedPart) {

        for(Part p : associatedParts){
            if(p.getId() == selectedAssociatedPart.getId()){
                associatedParts.remove(p);
                return true;
            }
        }
        return false;
    }

    /**
     * @return The associated parts
      */
    public static ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }


}
