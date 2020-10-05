package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main class
 * @author Fernando Rosa
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
        primaryStage.setTitle("Software 1 GUI Mock UP");
        primaryStage.setScene(new Scene(root, 1300, 700));
        primaryStage.show();

    }

    /**
     * This will open the requested form
     * It will set the scene
     * It will set the size of the form
     * @param actionEvent ActionEvent
     * @param loadForm Form to be load
     */
    public static void callForms(ActionEvent actionEvent, String loadForm) throws IOException {

        int w = 1300, h = 700;

        if (loadForm.toLowerCase().equals("addpartform.fxml") || loadForm.toLowerCase().equals("modifypartform.fxml")) {
            w = 600;
            h = 800;
        }

        Parent addPartForm = FXMLLoader.load(Main.class.getResource(loadForm));

        Stage form = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        form.setScene(new Scene(addPartForm, w, h));
        form.show();
    }

    public static void main(String[] args) {

        InHouse in = new InHouse(1, "Bike Chain", 10.50, 5, 2, 7, 201004);
        InHouse on = new InHouse(2, "Stand", 45.36, 10, 1, 30, 8273498);
        Outsourced en = new Outsourced(3, "Brakes", 33.45, 40, 2, 100, "Winter");
        InHouse un = new InHouse(4, "Seat", 50, 5, 2, 7, 23423);
        Inventory.addPart(in);
        Inventory.addPart(on);
        Inventory.addPart(en);
        Inventory.addPart(un);
        Product oil = new Product(Inventory.getAllParts(), 1900, "Oil", 15.99, 50, 1, 100);
        Product tire = new Product(Inventory.getAllParts(), 1231, "Tire", 10.99, 3, 1, 100);
        Inventory.addProduct(oil);
        Inventory.addProduct(tire);
        launch(args);

    }
}
