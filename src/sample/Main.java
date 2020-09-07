package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
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

    public static void callForms(ActionEvent actionEvent, String loadForm) throws IOException {

        int w = 1300, h = 700;

        if(loadForm.toLowerCase().equals("addpartform.fxml")) {
            w = 600;
            h = 800;
        }

        Parent addPartForm = FXMLLoader.load(Main.class.getResource(loadForm));
        Stage form = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        form.setScene(new Scene(addPartForm, w, h));
        form.show();

    }




    public static void main(String[] args) {
        InHouse in = new InHouse(1,  "Part", 2.5, 5, 2, 7, 2);
        InHouse on = new InHouse(2,  "kjdf", 2.5, 5, 2, 7, 2);
        Inventory.addPart(in);
        Inventory.addPart(on);
        Product bookshelf = new Product(Inventory.getAllProducts(), 1, "Bookshelf", 199.99, 50, 1, 100);
        Inventory.addProduct(bookshelf);
        launch(args);

    }
}
