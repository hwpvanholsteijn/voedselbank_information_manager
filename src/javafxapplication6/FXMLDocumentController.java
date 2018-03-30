package javafxapplication6;

import Lijsten.Login;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXPasswordField Wachtwoord;   
    @FXML
    private JFXTextField Gebruikersnaam;      
    @FXML
    private AnchorPane rootPane;
    
    public static Connection conn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try { 
            conn = SimpleDataSourceV2.getConnection();
        } catch (SQLException ex) {
            System.out.println("ERRROOOOOOOOR");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }    

    @FXML
    private void loginToProgram(ActionEvent event) throws IOException  {         
        try {           
            if (isLogin(Gebruikersnaam.getText(), Wachtwoord.getText())) {
                changeScene();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);        
                alert.setHeaderText("Gebruikersnaam/Wachtwoord onjuist.");
                alert.setContentText("Probeer het opnieuw.");
        
                alert.showAndWait();
            }               
        }
        
        catch (SQLException e){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
//            alert.setHeaderText("Er is een fout in het systeem.");
//            alert.setContentText("Neem contact op met ICT beheer.");
//        
//            alert.showAndWait();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setHeaderText("Connectie is verbroken.");
            alert.setContentText("Controleer uw verbinding.");
            alert.showAndWait();
        }       
    }
    
    private void changeScene() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    public void logOut() throws IOException {        
        SceneController c = HomePageController.getSceneControl();
        c.setRoot(rootPane);
        c.setScene(99);        
    }

    private boolean isLogin(String gebString, String wachtwoordString) throws IOException, SQLException {       
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM LoginGegevens WHERE LoginNaam = ? AND LoginWachtwoord = ?"; 
        
        Login.loginNaam = gebString;
        Login.wachtwoord = wachtwoordString;
        
        
        try {            
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, gebString);
            preparedStatement.setString(2, wachtwoordString);;

            resultSet = preparedStatement.executeQuery();
            return resultSet.next();        
        }

        catch ( SQLException e){
            System.out.print(e);
        }  
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setHeaderText("Connectie is verbroken.");
            alert.setContentText("Controleer uw verbinding.");
        
            alert.showAndWait();
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
        return false;
    }    
}
