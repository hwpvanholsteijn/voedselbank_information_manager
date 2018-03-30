package javafxapplication6;


import Filters.MenuFilter;
import Lijsten.Lijst;
import Lijsten.Statements;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class Home_SceneController implements Initializable {
    
    Parent root;
    @FXML
    private JFXButton resetButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Lijst.medewerkerUpdate();
//        Lijst.IntakeDatabaseUpdate();
//        Lijst.clientDatabaseUpdate();
//        Lijst.medewerkerUpdate();
    }

    @FXML
    private void setUit(ActionEvent event) throws IOException {
        HomePageController.MenuFilter.setHigh(1);
        HomePageController.sceneControl.setScene(1); 
    }

    @FXML
    private void setBev(ActionEvent event) throws IOException {
        HomePageController.MenuFilter.setHigh(2);
        HomePageController.sceneControl.setScene(2); 
    }

    @FXML
    private void setClient(ActionEvent event) throws IOException {
        HomePageController.MenuFilter.setHigh(3);
        HomePageController.sceneControl.setScene(3); 
    }
    
    @FXML
    private JFXButton bevooradingslijst;

    @FXML
    private JFXButton uitgiftpuntKnop;

    @FXML
    private JFXButton clientKnop;    
}
