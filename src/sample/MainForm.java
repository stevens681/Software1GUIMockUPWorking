package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static javax.swing.JOptionPane.showMessageDialog;


public class MainForm {

    @FXML
    private TableView<Part> partTbl;        //Part's Table
    @FXML
    private TableView<Product> prodTbl;     //Product Table
    @FXML
    private TextField searchPart;           //Searchbar for part
    @FXML
    private TextField searchProd;           //Searchbar for product
    Parent parent;
    Stage stage;
    //Exit button
    @FXML
    private void exit(ActionEvent actionEvent){
        System.exit(0);
    }

    //Opens the add part form
    @FXML
    public void addPartBtn(ActionEvent a) throws IOException {

        Main.callForms(a, "AddPartForm.fxml");

    }

    //Opens the add product form
    @FXML
    public void addProdBtn(ActionEvent a) throws IOException {

        Main.callForms(a, "AddProductForm.fxml");

    }

    @FXML
    public void modPartBtn(ActionEvent a) throws IOException {
        Part part = partTbl.getSelectionModel().getSelectedItem();

        if(part == null)
            showMessageDialog(null, "Please select a part");
        else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPartForm.fxml"));
            loader.load();
            ModifyPartForm selected = loader.getController();
            selected.selectedPart(partTbl.getSelectionModel().getSelectedItem());
            parent = loader.getRoot();
            stage = (Stage) ((Node)a.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();

        }


    }

    /**
     * This will search for a part
     * It will select the row if the search is done by ID
     * It will filter out the part that is looking for if is search by name
     * It will set the list to what it was after the search is done
     * */
    @FXML
    public void searchPart(ActionEvent e) throws Exception{

        String search = searchPart.getText().toLowerCase();     //Gets the text from the text field
        ObservableList<Part> items = FXCollections.observableArrayList();   //Creates a new observable list
        boolean searchById = false, notFound = false;

        //Checks if not empty
        if(!searchPart.getText().isEmpty()) {

            //Goes through the list
            for(Part part : Inventory.getAllParts()){
                try {   //Tries to check if the value from the text field is an integer or String

                    int id = Integer.parseInt(search);  //Assigns the integer

                    if(id == part.getId()){     //Checks for the ID number

                        partTbl.setItems(Inventory.getAllParts());      //In case the table was filtered
                        partTbl.getSelectionModel().select(Inventory.lookupPart(part.getId()));     //Selects the table row
                        searchById = true;
                        notFound = false;

                        break;
                    }else {
                        searchById = true;
                        notFound = true;
                    }

                }

                //If the text field is a string then do this
                catch (NumberFormatException exception ){

                    //Compare the part name to the initial string from the search bar
                    if(part.getName().toLowerCase().startsWith(search)){

                        items.add(Inventory.lookupPart(part.getId()));  //Add to my empty list

                    }
                }
            }

            if(!searchById){
                //If the new list is not empty
                if(!items.isEmpty()) {

                    partTbl.setItems(items);    //Add all the parts to the table from the new list
                    partTbl.getSelectionModel().clearSelection();   //Clear if it something was selected
                }
                else {

                    partTbl.setItems(Inventory.getAllParts());
                    showMessageDialog(null, "Part Name not found, Try another Part Name");
                    partTbl.getSelectionModel().clearSelection();
                    searchPart.clear();
                }
            }
            if(notFound){

                showMessageDialog(null, "Part ID not found, Try another ID number");    //Displays an error
                partTbl.getSelectionModel().clearSelection();
            }

        }
        else {

            //Shows and error and clean previous searches
            showMessageDialog(null, "To search you must type a \"Part ID\" or \"Part Name\"");
            partTbl.getSelectionModel().clearSelection();
            partTbl.setItems(Inventory.getAllParts());
            searchPart.clear();
        }
    }

    /**
     * This delete a selected part
     * Updates the table view
     * */
    @FXML
    public void deletePart(ActionEvent e){

        Part part = partTbl.getSelectionModel().getSelectedItem();      //Gets the selected part
        Inventory.deletePart(part);     //Deletes the part
        partTbl.setItems(Inventory.getAllParts());      //Updates the table
    }

    /**
     * This delete a selected product
     * Updates the table view
     * */
    @FXML
    public void deleteProduct(ActionEvent e){

        Product product = prodTbl.getSelectionModel().getSelectedItem();        //Gets the selected product
        Inventory.deleteProduct(product);       //Deletes the product
        prodTbl.setItems(Inventory.getAllProducts());       //Updates the table
    }

    /**
     * This will search for a product
     * It will select the row if the search is done by ID
     * It will filter out the product that is looking for if is search by name
     * It will set the list to what it was after the search is done
     * */
    @FXML
    public void searchProd(ActionEvent e) throws Exception{

        String search = searchProd.getText().toLowerCase();     //Gets the text from the text field
        ObservableList<Product> items = FXCollections.observableArrayList();   //Creates a new observable list
        boolean searchById = false, notFound = false;

        //Checks if not empty
        if(!searchProd.getText().isEmpty()) {

            //Goes through the list
            for(Product product : Inventory.getAllProducts()){
                try {   //Tries to check if the value from the text field is an integer or String

                    int id = Integer.parseInt(search);  //Assigns the integer

                    if(id == product.getId()){     //Checks for the ID number

                        prodTbl.setItems(Inventory.getAllProducts());      //In case the table was filtered
                        prodTbl.getSelectionModel().select(Inventory.lookupProduct(product.getId()));     //Selects the table row
                        searchById = true;
                        notFound = false;


                        break;
                    }
                    else{
                        searchById = true;
                        notFound = true;

                    }
                }

                //If the text field is a string then do this
                catch (NumberFormatException exception ){

                    //Compare the product name to the initial string from the search bar
                    if(product.getName().toLowerCase().startsWith(search)){

                        items.add(Inventory.lookupProduct(product.getId()));  //Add to my empty list

                    }
                }
            }


            if(!searchById){
                //If the new list is not empty
                if(!items.isEmpty()) {

                    prodTbl.setItems(items);    //Add all the product to the table from the new list
                    prodTbl.getSelectionModel().clearSelection();   //Clear if it something was selected
                }
                else {

                    prodTbl.setItems(Inventory.getAllProducts());
                    showMessageDialog(null, "Part Name not found, Try another Product Name");
                    prodTbl.getSelectionModel().clearSelection();
                    searchProd.clear();
                }
            }

            if(notFound){

                showMessageDialog(null, "Product ID not found, Try another ID number");    //Displays an error
                prodTbl.getSelectionModel().clearSelection();
            }

        }
        else {

            //Shows and error and clean previous searches
            showMessageDialog(null, "To search you must type a \"Product ID\" or \"Product Name\"");
            prodTbl.getSelectionModel().clearSelection();
            prodTbl.setItems(Inventory.getAllProducts());
            searchProd.clear();
        }
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
