
package javafxapplication6;

import ExcelWriter.ExcelWriter;
import ExcelWriter.ProductielijstExcelTemplate;
import Lijsten.Lijst;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dataModels.Uitgiftepunt;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Lijst_SceneController implements Initializable {
    
    private final ArrayList<String> TemplateNamen = new ArrayList<>();
    ObservableList<String> templateObservableList = FXCollections.observableList(TemplateNamen);
    
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");
    public static String datumString;

    @FXML
    private Text weekNr;
    @FXML
    private TableColumn<Uitgiftepunt, String> uitgiftepunt;
    @FXML
    private TableColumn<Uitgiftepunt, String> adres;
    @FXML
    private TableColumn<Uitgiftepunt, String> postcode;
    @FXML
    private TableColumn<Uitgiftepunt, String> plaats;
    @FXML
    private TableColumn<Uitgiftepunt, Integer> pakket1;
    @FXML
    private TableColumn<Uitgiftepunt, Integer> pakket2;
    @FXML
    private TableColumn<Uitgiftepunt, Integer> pakket3;
    @FXML
    private TableColumn<Uitgiftepunt, Integer> pakketTotaal;
    @FXML
    private TableView<Uitgiftepunt> tableview;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton saveTemplate;
    @FXML
    private TextField textTemplate;
    @FXML
    private DatePicker datumG;
    @FXML
    private Text datumGekozen;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
        datumString = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String datum;
        datum = dtf.format(localDate);
        datumGekozen.setText(datum);
        
        try {
            Lijst.updateList();
        } catch (SQLException ex) {
            Logger.getLogger(Lijst_SceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setHeaderText("Connectie is verbroken.");
            alert.setContentText("Controleer uw verbinding.");
            alert.showAndWait();
        }

        tableview.setItems(Lijst.bevoorradingsObservableList);
        
        postcode.setCellValueFactory(
                new PropertyValueFactory<>("postcode"));

        uitgiftepunt.setCellValueFactory(
                new PropertyValueFactory<>("naam"));

        adres.setCellValueFactory(
                new PropertyValueFactory<>("adres"));
        
        plaats.setCellValueFactory(
                new PropertyValueFactory<>("plaats"));

        pakket1.setCellValueFactory(
                new PropertyValueFactory<>("enkelvoudigPakket"));

        pakket2.setCellValueFactory(
                new PropertyValueFactory<>("dubblePakket"));

        pakket3.setCellValueFactory(
                new PropertyValueFactory<>("drievoudigPakket"));
        
        pakketTotaal.setCellValueFactory(
                new PropertyValueFactory<>("teLeverenPakketen"));
        
        tableview.setRowFactory(tableviewer -> {
            TableRow<Uitgiftepunt> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (! row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE))) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    Uitgiftepunt draggedPerson = tableview.getItems().remove(draggedIndex);

                    int dropIndex ; 

                    if (row.isEmpty()) {
                        dropIndex = tableview.getItems().size() ;
                    } else {
                        dropIndex = row.getIndex();
                    }
                    tableview.getItems().add(dropIndex, draggedPerson);
                    event.setDropCompleted(true);
                    tableview.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row ;
        }); 
    }
        

    @FXML
    private void weekNext(ActionEvent event) throws SQLException {        
        
    }

    @FXML
    private void weekBack(ActionEvent event) {
    }
    
    private void path(){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Kies opslaan locatie");
        File dir = chooser.showDialog(new Stage());
        if (dir != null) {
        ProductielijstExcelTemplate.fileOutputPath = dir.toString() + "" + "/Productielijst Voedselbank.xlsx";
        }
    }
    
    @FXML
    private void save(ActionEvent event) {
        path();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Opslaan");
        alert.setHeaderText("Weet u zeker dat u het document wilt opslaan?");
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get() == ButtonType.OK) {
            ExcelWriter ew = new ExcelWriter(new ProductielijstExcelTemplate(Lijst.bevoorradingslijstList));
            ew.MakeExcel();
        } 
    }
    




    @FXML
    private void datumGekozen(ActionEvent event) throws SQLException {
        datumString = datumG.getValue().toString();
        datumGekozen.setText(datumString);
        Lijst.updateList();
        tableview.setItems(Lijst.bevoorradingsObservableList);
    }
}