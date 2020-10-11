package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This is the controller for Modify Part Form
 * @author Fernando Rosa
 */
public class ModifyPartForm {

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

    public int id;

    /**
     * Selects the radios button
     * Fills the field text areas with the passed part
     * @param part The part and it's fields
     */
    public void selectedPart(Part part) {

        if (part instanceof InHouse) {
            inHouRadio.setSelected(true);
            macCopText.setText(Integer.toString(((InHouse) part).getMachineId()));
        } else {
            outRadio.setSelected(true);
            macCopText.setText(((Outsourced) part).getCompanyName());
        }

        //Fills the areas
        id = part.getId();
        idText.setText(Integer.toString(part.getId()));
        nameText.setText(part.getName());
        invText.setText(Integer.toString(part.getStock()));
        priceText.setText(Double.toString(part.getPrice()));
        maxText.setText(Integer.toString(part.getMax()));
        minText.setText(Integer.toString(part.getMin()));
    }

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
     *Save button, this will check all the fields and modify them to the part list
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

        if (nameText.getText().isEmpty() || invText.getText().isEmpty() ||
                priceText.getText().isEmpty() || maxText.getText().isEmpty() || minText.getText().isEmpty()
                || macCopText.getText().isEmpty()) {

            showMessageDialog(null, "Please fill all the fields");
            return false;
        } else
            return true;
    }
}
