/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddMede_SceneController implements Initializable {

    @FXML
    private JFXButton resetButton;
    @FXML
    private Pane AanPane;
    @FXML
    private JFXTextField naamMedewerker;
    @FXML
    private Pane AanPane1;
    @FXML
    private JFXTextField gebrNaam;
    @FXML
    private JFXTextField passGb;
    @FXML
    private JFXComboBox<?> medewerker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddMedewerker(ActionEvent event) {
    }

    @FXML
    private void AddGebruiker(ActionEvent event) {
    }
    
}
