/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import ExcelWriter.ExcelWriter;
import ExcelWriter.ProductielijstExcelTemplate;
import Lijsten.Lijst;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Database_SceneController implements Initializable {

    @FXML
    private JFXButton resetButton;
    @FXML
    private Pane AanPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void path(){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Kies opslaan locatie");
        File dir = chooser.showDialog(new Stage());
        if (dir != null) {
        ProductielijstExcelTemplate.fileOutputPath = dir.toString() + "" + "/Productielijst Voedselbank.xlsx";
        }
    }
    
    private void toevoegen() {
        path();        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Toevoegen");
        alert.setHeaderText("Weet u zeker dat u een document wilt toevoegen?");
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get() == ButtonType.OK) {
            //gooi het hier in
        } 
    }

    @FXML
    private void AddItake(ActionEvent event) {
        toevoegen();
    }
    
}
