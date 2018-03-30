package javafxapplication6;

import Filters.ClientFilter;
import Lijsten.Lijst;
import dataModels.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class ClientenOpvragen_SceneController implements Initializable {

    @FXML
    private Pane OpPane;
    @FXML
    private TableView<Client> tableView;
    
    @FXML
    private TableColumn<Client, String> plaats;

    @FXML
    private TableColumn<Client, String> postcode;

    @FXML
    private TableColumn<Client, String> stopDatum;

    @FXML
    private TableColumn<Client, String> kaartnummer;

    @FXML
    private TableColumn<Client, Integer> aantalPakketten;

    @FXML
    private TableColumn<Client, String> naam;

    @FXML
    private TableColumn<Client, Integer> aantalPersonen;

    @FXML
    private TableColumn<Client, String> herintakeDatum;

    @FXML
    private TableColumn<Client, String> mobiel;

    @FXML
    private TableColumn<Client, String> telefoon;

    @FXML
    private TableColumn<Client, String> startDatum;

    @FXML
    private TableColumn<Client, String> email;

    @FXML
    private TableColumn<Client, String> contactPersoon;

    @FXML
    private TableColumn<Client, String> status;
    
    @FXML
    private TableColumn<Client, String> uitgiftepunt;
    
    @FXML
    private TableColumn<Client, String> adres;
    
   
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        tableView.getColumns().clear();
        tableView.setItems(Lijst.clientObservableList);
                addColumns();
        
        
        plaats.setCellValueFactory(
                new PropertyValueFactory<>("plaats"));

        postcode.setCellValueFactory(
                new PropertyValueFactory<>("postcode"));
        
        stopDatum.setCellValueFactory(
                new PropertyValueFactory<>("stopDatum"));
        
        startDatum.setCellValueFactory(
                new PropertyValueFactory<>("startDatum"));
                
        herintakeDatum.setCellValueFactory(
                new PropertyValueFactory<>("herintakeDatum"));
        
        kaartnummer.setCellValueFactory(
                new PropertyValueFactory<>("kaartnummer"));
        
        aantalPakketten.setCellValueFactory(
                new PropertyValueFactory<>("aantalPakketen"));
       
        naam.setCellValueFactory(
                new PropertyValueFactory<>("naam"));        
       
        contactPersoon.setCellValueFactory(
                new PropertyValueFactory<>("Contactpersoon"));
        
        aantalPersonen.setCellValueFactory(
                new PropertyValueFactory<>("AantalPersonen"));
        
        mobiel.setCellValueFactory(
                new PropertyValueFactory<>("mobieleTelefoonnummer"));
        
        telefoon.setCellValueFactory(
                new PropertyValueFactory<>("telefoonnummer"));
        
        email.setCellValueFactory(
                new PropertyValueFactory<>("Email"));
        
        contactPersoon.setCellValueFactory(
                new PropertyValueFactory<>("Contactpersoon"));
        
        status.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        
        uitgiftepunt.setCellValueFactory(
                new PropertyValueFactory<>("Uitgiftepunt"));
        
        adres.setCellValueFactory(
                new PropertyValueFactory<>("adres"));

    }    
    
    
     private void addColumns() {
            tableView.getColumns().add(kaartnummer);
        
        if(ClientFilter.naam)
            tableView.getColumns().add(naam);
        
        if(ClientFilter.aantalPakketen)        
            tableView.getColumns().add(aantalPersonen);
        
        if(ClientFilter.aantalPersonen)  
            tableView.getColumns().add(aantalPakketten);
        
        if(ClientFilter.adres)  
            tableView.getColumns().add(adres);
        
        if(ClientFilter.email)  
            tableView.getColumns().add(email);
        
        if(ClientFilter.mobiel)  
            tableView.getColumns().add(mobiel);
         
        if(ClientFilter.telefoon)  
            tableView.getColumns().add(telefoon);
          
        if(ClientFilter.postcode)  
            tableView.getColumns().add(postcode);
           
        if(ClientFilter.plaats)  
            tableView.getColumns().add(plaats);
            
        if(ClientFilter.contactpersoon)  
            tableView.getColumns().add(contactPersoon);
             
        if(ClientFilter.statuc)  
            tableView.getColumns().add(status);
              
        if(ClientFilter.herintake)  
            tableView.getColumns().add(herintakeDatum);
               
        if(ClientFilter.startDatum)  
            tableView.getColumns().add(startDatum);
        if(ClientFilter.stopDatum)  
            tableView.getColumns().add(stopDatum);
         
        if(ClientFilter.uitgiftpunt)  
            tableView.getColumns().add(uitgiftepunt);
        
    }

    @FXML
    private void backButton(MouseEvent event) throws IOException {
        HomePageController.sceneControl.setScene(3);
    }
    
}
