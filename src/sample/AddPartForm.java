package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class AddPartForm {
    @FXML
    private RadioButton inHouRadio;
    @FXML
    private RadioButton outRadio;
    @FXML
    private Text inOutLabel;
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
    private void inRadioBtn(ActionEvent e){

        inOutLabel.setText("Machine ID");
        outRadio.setSelected(false);

    }

    @FXML
    private void outRadioBtn(ActionEvent e){

        inOutLabel.setText("Company Name");
        inHouRadio.setSelected(false);

    }

    @FXML
    private void saveBtn(ActionEvent e) throws IOException{
        int id = Inventory.getAllParts().size() + 1;        //This generates the ID

        if(emptyField()){

            if(inHouRadio.isSelected()){

                String name = nameText.getText();
                double price = Double.parseDouble(priceText.getText());
                int inv = Integer.parseInt(invText.getText()), min = Integer.parseInt(minText.getText()),
                        max = Integer.parseInt(maxText.getText()), macID = Integer.parseInt(macCopText.getText());

                InHouse newPart = new InHouse(id, name, price, inv, min, max,macID);
                Inventory.addPart(newPart);

            } else if(outRadio.isSelected()){

                String name = nameText.getText(), macID = macCopText.getText();
                double price = Double.parseDouble(priceText.getText());
                int inv = Integer.parseInt(invText.getText()), min = Integer.parseInt(minText.getText()),
                        max = Integer.parseInt(maxText.getText());

                Outsourced newPart = new Outsourced(id, name, price, inv, min, max,macID);
                Inventory.addPart(newPart);

            }

            Main.callForms(e, "MainForm.fxml"); //Calls the main form

        }

    }

    //This will take you back to the main form
    @FXML
    private void cancelBtn(ActionEvent a) throws IOException {

        Main.callForms(a, "MainForm.fxml"); //Calls the main form

    }

    private boolean emptyField(){

        if(nameText.getText().isEmpty() || invText.getText().isEmpty() ||
        priceText.getText().isEmpty() || maxText.getText().isEmpty() ||minText.getText().isEmpty()
        || macCopText.getText().isEmpty()) {

            showMessageDialog(null, "Please fill all the fields");
            return false;
        }
        else
            return true;
    }

    @FXML
    public void initialize(){
        inHouRadio.setSelected(true);


    }


}
