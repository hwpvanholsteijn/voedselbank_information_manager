/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import Filters.ClientFilter;
import Filters.UitgiftenpuntFilter;
import Lijsten.Lijst;
import Lijsten.Statements;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Max
 */
public class Clienten_SceneController implements Initializable {

    @FXML
    private JFXComboBox<String> plaatsCombo;
    @FXML
    private JFXComboBox<String> statusCombo;
    @FXML
    private JFXButton resetButton;
    @FXML
    private JFXCheckBox Naam;
    @FXML
    private JFXCheckBox Email;
    @FXML
    private JFXCheckBox Mobiel;
    @FXML
    private JFXCheckBox Telefoon;
    @FXML
    private JFXCheckBox Postcode;
    @FXML
    private JFXCheckBox Plaats;
    @FXML
    private JFXCheckBox antPersonen;
    @FXML
    private JFXCheckBox antPakketten;
    @FXML
    private JFXCheckBox Uitgiftepunt;
    @FXML
    private JFXCheckBox Status;
    @FXML
    private JFXCheckBox Contactpersoon;
    @FXML
    private JFXCheckBox startDate;
    @FXML
    private JFXCheckBox stopDate;
    @FXML
    private JFXCheckBox herDate;
    @FXML
    private JFXCheckBox Adres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Lijst.updateList();
            updateComboBoxPlaats();
            updateComboBoxStatus();



        } catch (SQLException ex) {
            Logger.getLogger(Clienten_SceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setHeaderText("Connectie is verbroken.");
            alert.setContentText("Controleer uw verbinding.");
        
            alert.showAndWait();
        }
        plaatsCombo.setVisible(false);
        statusCombo.setVisible(false);        
    }    
    
    private final ArrayList<String> plaatsNamen = new ArrayList<>();
    ObservableList<String> plaatsNamenObservableList = FXCollections.observableList(plaatsNamen);
    
    private final ArrayList<String> statussen = new ArrayList<>();
    ObservableList<String> statusObservableList = FXCollections.observableList(statussen);
    
    @FXML
    private void showPlaats(ActionEvent event) {
        if (Plaats.isSelected()) {
            plaatsCombo.setVisible(true);
        } else {
            plaatsCombo.setVisible(false);
        }
    }

    @FXML
    private void showStatus(ActionEvent event) {
        if (Status.isSelected()) {
            statusCombo.setVisible(true);
        } else {
            statusCombo.setVisible(false);
        }
    }



    @FXML
    private void showOverzicht(ActionEvent event) throws IOException {
        ClientFilter.naam = Naam.isSelected();
        ClientFilter.mobiel = Mobiel.isSelected();
        ClientFilter.telefoon = Telefoon.isSelected();
        ClientFilter.email = Email.isSelected();
        ClientFilter.postcode = Postcode.isSelected();
        ClientFilter.plaats = Plaats.isSelected();
        ClientFilter.aantalPakketen = antPakketten.isSelected();
        ClientFilter.aantalPersonen = antPersonen.isSelected();
        ClientFilter.uitgiftpunt = Uitgiftepunt.isSelected();
        ClientFilter.statuc = Status.isSelected();
        ClientFilter.contactpersoon = Contactpersoon.isSelected();
        ClientFilter.startDatum = startDate.isSelected();
        ClientFilter.stopDatum = stopDate.isSelected();
        ClientFilter.herintake = herDate.isSelected();
        ClientFilter.adres = Adres.isSelected();
        ClientFilter.plaatsVoorkeur = plaatsCombo.getSelectionModel().getSelectedItem();
        ClientFilter.statusVoorkeur = statusCombo.getSelectionModel().getSelectedItem();
        
        try {
            Lijst.updateList();
        } catch (SQLException ex) {
            Logger.getLogger(Clienten_SceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setHeaderText("Connectie is verbroken.");
            alert.setContentText("Controleer uw verbinding.");
        
            alert.showAndWait();
        } 
        finally {
           HomePageController.sceneControl.setScene(11); 
        }        
    }
    
    private void updateComboBoxPlaats() throws SQLException {
        plaatsCombo.getItems().removeAll(plaatsNamenObservableList);
        plaatsNamenObservableList.add(0, "Geen voorkeur");

        for (int i = 0; i < Lijst.clientenListPlaats.size(); i++) {
            plaatsNamenObservableList.add(i + 1, Lijst.clientenListPlaats.get(i).getPlaats());
        }
        plaatsCombo.setItems(plaatsNamenObservableList);
        plaatsCombo.getSelectionModel().selectFirst();

    }
    
    private void updateComboBoxStatus() throws SQLException {
        statusCombo.getItems().removeAll(plaatsNamenObservableList);
        statusObservableList.add(0, "Geen voorkeur");
        statusObservableList.add(1, "Actief");
        statusObservableList.add(2, "Gestopt");
        statusObservableList.add(3, "Afgewezen");
        statusCombo.setItems(statusObservableList);
        statusCombo.getSelectionModel().selectFirst();
    }
}
