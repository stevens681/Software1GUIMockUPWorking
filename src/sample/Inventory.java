package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static javax.swing.JOptionPane.showMessageDialog;

public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        boolean flag = true;

        //This checks the list for repeated items
        for(Part pt: allParts){
            if(newPart.getName().toLowerCase().equals(pt.getName().toLowerCase())){
                flag = false;
                System.out.println("part " + allParts);
            }
        }


        if(flag){
            allParts.add(newPart); //This is the part the adds to the list

        }
        else {

        }

    }

    public static void addProduct(Product newProduct){
        boolean flag = true;

        for(Product pr: allProducts){
            if(newProduct.getName().toLowerCase().equals(pr.getName().toLowerCase())){
                flag = false;
                System.out.println("This is inside the loop");

            }
        }

        if (flag) {
            allProducts.add(newProduct);
            System.out.println("Product This is working");
            showMessageDialog(null, "This is product");
        }
        else {
            showMessageDialog(null, "bad");

        }

    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
