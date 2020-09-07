package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;


import java.io.IOException;


public class MainForm {

    @FXML
    private TableView<Part> partTbl;        //Part's Table
    @FXML
    private TableView<Product> prodTbl;     //Product Table
    private ObservableList<String> items = FXCollections.observableArrayList();

    //Exit button
    @FXML
    private void exit(ActionEvent actionEvent){
        System.exit(0);
    }

    //Opens the add part form
    @FXML
    public void addPartbtn(ActionEvent a) throws IOException {

        Main.callForms(a, "AddPartForm.fxml");

    }

    /**
     * This will create columns for each tableView
     * also set the with for each one of it
     * and has the label and what part or product goes into the fields
     * */
    public void colCreator(String tbls){

        String[] lblPart = {"Part Id", "Part Name", "Inventory Level", "Price// Cost per Unit"};            //Labels for the part table columns
        String[] lblProduct = {"Product Id", "Product Name", "Inventory Level", "Price// Cost per Unit"};   //Labels for the product table columns
        String[] areas = {"id", "name", "stock", "price"};      //The values for the fields
        int colWidth = 125;     //Holds the first 3 width

        //This creates and fills the part tableView
        if(tbls.toLowerCase().equals("part")){
            partTbl.setItems(Inventory.getAllParts());      //Gets all the parts

            for(int i =0; i<4; i++){
                if(areas[i].equals("price"))
                    colWidth = 175;

                TableColumn column = new TableColumn(lblPart[i]);
                column.setCellValueFactory(new PropertyValueFactory<Part, String>(areas[i]));
                column.setMinWidth(colWidth);
                partTbl.getColumns().addAll(column);        //Adds all the columns for the part's table
            }

        }

        //This creates and fills the product tableView
         if(tbls.toLowerCase().equals("product")){
            prodTbl.setItems(Inventory.getAllProducts());       //Gets all the products

            for(int i =0; i<4; i++){
                if(areas[i].equals("price"))
                    colWidth = 175;

                TableColumn column = new TableColumn(lblProduct[i]);
                column.setCellValueFactory(new PropertyValueFactory<Product, String>(areas[i]));
                column.setMinWidth(colWidth);
                prodTbl.getColumns().addAll(column);        //Adds all the columns for the product table
            }

        }

    }

    @FXML
    public void initialize() {

        colCreator("part");
        colCreator("product");

    }

}
