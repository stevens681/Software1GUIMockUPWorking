package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This is the controller for Add Part Form
 * @author Fernando Rosa
 */
public class AddPartForm {

    //Elements from the form
    @FXML
    private RadioButton inHouRadio;
    @FXML
    private RadioButton outRadio;
    @FXML
    private Text inOutLabel;
    @FXML
    private TextField idText;
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
    @FXML
    private TextField macCopText;
    @FXML
    private Text logicError;

    int id = Inventory.getAllParts().size() + 1;        //This generates the ID

    /**
     * When the In-House radio button is selected
     * @param e ActionEvent
     */
    @FXML
    private void inRadioBtn(ActionEvent e) {

        inOutLabel.setText("Machine ID");       //Changes the label name
        outRadio.setSelected(false);            //Deselect Outsourced radio
        macCopText.clear();
    }

    /**
     * When the outsourced radio is selected
     * @param e ActionEvent
     */
    @FXML
    private void outRadioBtn(ActionEvent e) {

        inOutLabel.setText("Company Name");     //Changes the label name
        inHouRadio.setSelected(false);          //Deselect In-House radio
        macCopText.clear();
    }

    /**
     * Save button, this will check all the fields and add them to the part list
     * @param e ActionEvent
     * @throws IOException Failed to save the part
     */
    @FXML
    private void saveBtn(ActionEvent e) throws IOException {


        //This check for any empty fields
        if (emptyField()) {

            //Variables declaration
            String name = nameText.getText();
            double price = Double.parseDouble(priceText.getText());

            try{
                int inv = Integer.parseInt(invText.getText()), min = Integer.parseInt(minText.getText()),
                        max = Integer.parseInt(maxText.getText());

                if (min <= max) {
                    //Depending what radio button is selected this will add a part to in house or outsourced
                    if (inHouRadio.isSelected()) {

                        int macID = Integer.parseInt(macCopText.getText());
                        InHouse newPart = new InHouse(id, name, price, inv, min, max, macID);
                        Inventory.addPart(newPart);
                    } else if (outRadio.isSelected()) {

                        String copID = macCopText.getText();

                        Outsourced newPart = new Outsourced(id, name, price, inv, min, max, copID);
                        Inventory.addPart(newPart);
                    }
                    Main.callForms(e, "MainForm.fxml"); //Calls the main form back
                }
                else {
                    //Shows an error if min is higher than max and cleans the fields
                    showMessageDialog(null, "Minimal inventory cannot be grater than max");
                    minText.clear();
                    maxText.clear();
                }

            }catch (NumberFormatException exception){
                showMessageDialog(null, exception.getMessage()+" This value needs to be n integer");

            }


        }
    }

    /**
     * This will take you back to the main form
     * @param e ActionEvent
     * @throws IOException Failed to go back to the main form
     */
    @FXML
    private void cancelBtn(ActionEvent e) throws IOException {

        Main.callForms(e, "MainForm.fxml"); //Calls the main form

    }

    /**
     * Checks for empty text fields
     * @return If any field is empty
     */
    private boolean emptyField() {

        int max=0, min=0, inv=0;
        String txt = "";
        boolean flag = true;

        if(nameText.getText().isEmpty()) {
            txt += "The product name field is empty \n";
            flag = false;
        }
        if(invText.getText().isEmpty()){
            txt += "The product inventory field is empty \n";
            flag = false;
        }
        else {
            try{
                inv = Integer.parseInt(invText.getText());
            }
            catch (NumberFormatException exception){
                txt += "The product inventory must be an integer \n";
                flag = false;
            }
        }
        if(priceText.getText().isEmpty()){
            txt += "The product price field is empty \n";
            flag = false;
        }
        else {
            try{
                Double.parseDouble(priceText.getText());
            }
            catch (NumberFormatException exception){
                txt += "The product price must be a double \n";
                flag = false;
            }
        }
        if(minText.getText().isEmpty()){
            txt += "The product min field is empty \n";
            flag = false;
        }
        else {
            try{
                min = Integer.parseInt(minText.getText());
            }
            catch (NumberFormatException exception){
                txt += "The product min inventory must be an integer \n";
                flag = false;
            }
        }

        if(inHouRadio.isSelected()){
            if(macCopText.getText().isEmpty()){
                txt += "The machine ID field is empty\n";
                flag = false;
            }
            else {
                try{
                    Integer.parseInt(macCopText.getText());
                }
                catch (NumberFormatException exception){
                    txt += "The machine ID needs to be an integer\n";
                    flag = false;

                }
            }
        }
        if(outRadio.isSelected()){
            if(macCopText.getText().isEmpty()){
                txt += "The company name field is empty\n";
                flag = false;
            }
        }
        if(maxText.getText().isEmpty()){
            txt += "The product max field is empty \n";
            flag = false;
        }
        else {
            try{
                max = Integer.parseInt(maxText.getText());
            }
            catch (NumberFormatException exception){
                txt += "The product max inventory must be an integer \n";
                flag = false;
            }
        }
        if (min > max){
            txt += "The product min inventory must be less than max inventory \n";
            flag = false;
        }
        if (inv > max){
            txt += "The product inventory must be less than max inventory \n";
            flag = false;
        }
        logicError.setText(txt);

        return flag;

    }

    /**
     * Initializes the form
     */
    @FXML
    public void initialize() {

        inHouRadio.setSelected(true);
    }
}
