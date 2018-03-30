package javafxapplication6;

import Filters.MenuFilter;
import Lijsten.Login;
import Lijsten.Statements;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class HomePageController implements Initializable {  
    
    public static SceneController sceneControl;
    public static MenuFilter MenuFilter;

    @FXML
    private Pane scenePane;    
    @FXML
    private Pane NavBar;  
    @FXML
    private AnchorPane root;
    @FXML
    public JFXButton BtnHome;
    @FXML
    public JFXButton BtnUitgifte;
    @FXML
    public JFXButton BtnLijst;
    @FXML
    public JFXButton BtnClienten;
    @FXML
    private Text naamGebruiker;
    
    private AnchorPane rootPane;
    @FXML
    private JFXButton Btnuseraanmaken;
    @FXML
    private JFXButton BtnIntake;
    
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        
        MenuFilter = new MenuFilter(BtnHome, BtnUitgifte, BtnLijst, BtnClienten, Btnuseraanmaken, BtnIntake);
       
        sceneControl = new SceneController(scenePane);
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Home_Scene.fxml"));
            scenePane.getChildren().setAll(pane);            
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("HomeScene laad niet");
            System.out.println(e);
        }
        
        try {         
            System.out.println(Statements.getNaamGebruiker(Login.loginNaam, Login.wachtwoord));
            naamGebruiker.setText(Statements.getNaamGebruiker(Login.loginNaam, Login.wachtwoord));            
        } catch (SQLException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setHeaderText("Connectie is verbroken.");
            alert.setContentText("Controleer uw verbinding.");
        
            alert.showAndWait();
        }
    }    

    @FXML
    public  void setHome(ActionEvent event) throws IOException {
        MenuFilter.setHigh(0);
        sceneControl.setScene(0);          
    }

    @FXML
    public void setUit(ActionEvent event) throws IOException {
        MenuFilter.setHigh(1);
        sceneControl.setScene(1);         
    }

    @FXML
    public void setBevoor(ActionEvent event) throws IOException {
        MenuFilter.setHigh(2);
        sceneControl.setScene(2);         
    }

    @FXML
    public void setClient(ActionEvent event) throws IOException {
        MenuFilter.setHigh(3);        
        sceneControl.setScene(3);         
    }  
    
    
    @FXML
    private void logOut(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Weet u zeker dat u het programma wilt verlaten?");
        
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get() == ButtonType.OK) {
            SimpleDataSourceV2.closeConnection ();
            System.exit(0);
        } 
    }

    public static SceneController getSceneControl() {
        return sceneControl;
    }

    public Pane getScenePane() {
        return scenePane;
    }   
    
    public static MenuFilter getMenuFilter() {
        return MenuFilter;
    }

    @FXML
    private void setIntake(ActionEvent event) throws IOException {
        MenuFilter.setHigh(4);        
        sceneControl.setScene(4); 
    }

    @FXML
    private void setMedewerker(ActionEvent event) throws IOException {
        MenuFilter.setHigh(5);        
        sceneControl.setScene(5); 
    }
   
    
    
    
    
}
