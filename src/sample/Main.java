package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
        primaryStage.setTitle("Software 1 GUI Mock UP");
        primaryStage.setScene(new Scene(root, 1300, 700));
        primaryStage.show();


    }

    /**
     * This will open the requested form
     * It will set the scene
     * It will set the size of the form
     */
    public static void callForms(ActionEvent actionEvent, String loadForm) throws IOException {

        int w = 1300, h = 700;

        if(loadForm.toLowerCase().equals("addpartform.fxml") || loadForm.toLowerCase().equals("modifypartform.fxml")) {
            w = 600;
            h = 800;
        }

        Parent addPartForm = FXMLLoader.load(Main.class.getResource(loadForm));

        Stage form = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        form.setScene(new Scene(addPartForm, w, h));
        form.show();

    }
//    public static void modForms(ActionEvent actionEvent, String loadForm, Part part) throws IOException {
//
//        int w = 1300, h = 700;
//
//        if(loadForm.toLowerCase().equals("fxml") || loadForm.toLowerCase().equals("modifypartform.fxml")) {
//            w = 600;
//            h = 800;
//        }
//
//
//        Parent addPartForm = FXMLLoader.load(Main.class.getResource(loadForm));
//        sele
//        Stage form = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//        form.setScene(new Scene(addPartForm, w, h));
//        form.show();
//    }




    public static void main(String[] args) {
        InHouse in = new InHouse(1,  "Part", 2.5, 5, 2, 7, 2);
        InHouse on = new InHouse(2,  "kjdf", 2.5, 5, 2, 7, 2);
        InHouse en = new InHouse(3,  "f", 2.5, 5, 2, 7, 2);
        InHouse un = new InHouse(4,  "Pa", 2.5, 5, 2, 7, 2);
        Inventory.addPart(in);
        Inventory.addPart(on);
        Inventory.addPart(en);
        Inventory.addPart(un);
        Product bookshelf = new Product(Inventory.getAllProducts(), 1, "Bookshelf", 199.99, 50, 1, 100);
        Inventory.addProduct(bookshelf);
        launch(args);

    }
}
