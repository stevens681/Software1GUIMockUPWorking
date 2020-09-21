package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class AddProductForm {

    @FXML
    private TableView<Part> partTbl;        //Part's Table
    @FXML
    private TableView<Product> prodTbl;     //Product Table
    private ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private TextField searchPart;           //Searchbar for product

    @FXML
    private TextField nameText;
    @FXML
    private TextField invText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField maxText;
    @FXML
    private TextField minText;


    //This will take you back to the main form
    @FXML
    private void cancelBtn(ActionEvent a) throws IOException {

        Main.callForms(a, "MainForm.fxml"); //Calls the main form

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

    @FXML
    public void saveProduct(ActionEvent e) throws IOException{

    }


    @FXML
    public void initialize() {

        colCreator("part");
        colCreator("product");

    }
}
