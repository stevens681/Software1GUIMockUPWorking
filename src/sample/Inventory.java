package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static javax.swing.JOptionPane.showMessageDialog;

public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();       //Part list
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();     //Product list

    /**
     * Adds a part
     */
    public static void addPart(Part newPart) {
        boolean flag = true;

        //This checks the list for repeated items
        for (Part pt : allParts) {
            if (newPart.getName().toLowerCase().equals(pt.getName().toLowerCase())) {
                flag = false;
            }
        }

        //If the part already exists displays a message
        if (flag) {
            allParts.add(newPart); //This is the part the adds to the list

        } else {
            showMessageDialog(null, "This part already exists!");
        }

    }

    /**
     * Adds a product
     */
    public static void addProduct(Product newProduct) {

        allProducts.add(newProduct);


    }

    /**
     * Return the fields for a part
     */
    public static Part lookupPart(int partId) {
        for (Part part : Inventory.getAllParts()) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * Return the fields for a product
     */
    public static Product lookupProduct(int productId) {
        for (Product product : Inventory.getAllProducts()) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * The part select will update with the new info
     */
    public static void updatePart(int index, Part selectedPart) {
        getAllParts().set(index - 1, selectedPart);
    }

    public static void updateProduct(int index, Product selectedProduct) {
        getAllProducts().set(index - 1, selectedProduct);
    }

    /**
     * Remove a part
     */
    public static void deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
    }

    /**
     * Remove a product
     */
    public static void deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
    }

    /**
     * Returns all the parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns all the product
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
