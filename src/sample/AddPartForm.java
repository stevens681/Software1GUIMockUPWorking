package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

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

    int id = Inventory.getAllParts().size() + 1;        //This generates the ID

    /**
     * When the In-House radio button is selected
     */
    @FXML
    private void inRadioBtn(ActionEvent e) {

        inOutLabel.setText("Machine ID");       //Changes the label name
        outRadio.setSelected(false);            //Deselect Outsourced radio

    }

    /**
     * When the outsourced radio is selected
     */
    @FXML
    private void outRadioBtn(ActionEvent e) {

        inOutLabel.setText("Company Name");     //Changes the label name
        inHouRadio.setSelected(false);          //Deselect In-House radio

    }

    /**
     * Save button, this will check all the fields and add them to the part list
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

    }

    /**
     * This will take you back to the main form
     */
    @FXML
    private void cancelBtn(ActionEvent a) throws IOException {

        Main.callForms(a, "MainForm.fxml"); //Calls the main form

    }

    /**
     * Checks for empty text fields
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

    /**
     * Initializes the form
     */
    @FXML
    public void initialize() {

        inHouRadio.setSelected(true);


    }


}
