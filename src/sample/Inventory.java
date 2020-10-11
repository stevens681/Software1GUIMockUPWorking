package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This the inventory
 * @author Fernando Rosa
 */
public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();       //Part list
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();     //Product list

    /**
     * Adds a part
     * @param newPart The new part to be add
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
     * @param newProduct Product to be set
     */
    public static void addProduct(Product newProduct) {

        allProducts.add(newProduct);
    }

    /**
     * Return the fields for a part
     * @param partId Part id to be set
     * @return The part id
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
     * @param productId Product it to be set
     * @return The product id
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
     * @param index What number is the part
     * @param selectedPart What part is selected
     */
    public static void updatePart(int index, Part selectedPart) {
        getAllParts().set(index - 1, selectedPart);
    }

    /**
     * The product select will update with the new info
     * @param index What number is the product
     * @param selectedProduct What product is selected
     */
    public static void updateProduct(int index, Product selectedProduct) {
        getAllProducts().set(index - 1, selectedProduct);
    }

    /**
     * Remove a part
     * @param selectedPart The selected part
     */
    public static void deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
    }

    /**
     * Remove a product
     * @param selectedProduct The selected product
     * @return If the part is deleted
     */
    public static boolean deleteProduct(Product selectedProduct) {
        for(Product p : allProducts) {
            if (p.getId() == selectedProduct.getId()) {
                allProducts.remove(p);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all the parts
     * @return All the parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns all the product
     * @return All the product
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
