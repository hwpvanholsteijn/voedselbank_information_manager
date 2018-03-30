package javafxapplication6;

import Lijsten.Lijst;
import Lijsten.Statements;
import Filters.UitgiftenpuntFilter;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Uitgifte_SceneController implements Initializable {

    private final ArrayList<String> UitgiftenpuntenNamen = new ArrayList<>();
    ObservableList<String> observableList = FXCollections.observableList(UitgiftenpuntenNamen);

    // BEGIN FMXL
    @FXML
    private JFXCheckBox postcodeCheckbox;
    @FXML
    private JFXCheckBox aantalClientenCheckbox;
    @FXML
    private JFXCheckBox capaciteitCheckbox;
    @FXML
    private JFXCheckBox AdresCheckbox;
    @FXML
    private JFXComboBox<String> uitgiftepuntenDrop11;
    @FXML
    private Pane AanPane;
    @FXML
    private Pane OpPane;
    @FXML
    private Pane BewPane;
    @FXML
    private JFXTextField adres;
    @FXML
    private JFXTextField maximumcapaciteitBewerken;
    @FXML
    private JFXTextField maximumcapaciteit;
    @FXML
    private JFXTextField postcode;
    @FXML
    private JFXTextField naam;

    @FXML
    private void opvragen(ActionEvent event) throws SQLException, IOException {
        UitgiftenpuntFilter.toonAdres = AdresCheckbox.isSelected();
        UitgiftenpuntFilter.capaciteit = capaciteitCheckbox.isSelected();
        UitgiftenpuntFilter.toonPostcode = postcodeCheckbox.isSelected();
        UitgiftenpuntFilter.aantalClienten = aantalClientenCheckbox.isSelected();
        HomePageController.sceneControl.setScene(10);
    }

    @FXML
    private void aanmaken(ActionEvent event) throws SQLException {
        try {
            Boolean naamLenght = isStringCorrectSize(30, naam.getText());
            Boolean postcodeLenght = isStringCorrectSize(7, postcode.getText());
            Boolean adresLenght = isStringCorrectSize(30, adres.getText());

            if (!postcodeLenght) {
                postcode.setText("");
                postcode.setPromptText("vb'3921XL'");
            }

            if (!naamLenght) {
                naam.setText("");
                naam.setPromptText("max 30 caracters");
            }

            if (!adresLenght) {
                adres.setText("");
                adres.setPromptText("max 10 caracters");
            }

            int maxCap = Integer.parseInt(maximumcapaciteit.getText());

            if (naamLenght && postcodeLenght && adresLenght) {
                Statements.uitgiftpuntToevoegen(naam.getText(), postcode.getText(), adres.getText(), maxCap);
                naam.setText("");
                maximumcapaciteit.setText("");
                adres.setText("");
                postcode.setText("");
                postcode.setPromptText("Postcode");
                adres.setPromptText("Adres");
                naam.setPromptText("Naam");
                maximumcapaciteit.setPromptText("Maximumcapaciteit");
                Lijst.updateList();
                updateComboBoxMax();
            }
        } catch (NumberFormatException NE) {
            maximumcapaciteit.setText("");
            maximumcapaciteit.setPromptText("Vul een getal in");
        } catch (SQLException e) {
            System.out.println("fout in sql");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setHeaderText("Connectie is verbroken.");
            alert.setContentText("Controleer uw verbinding.");
        
            alert.showAndWait();
        }
    }

    @FXML
    private void bewerken(ActionEvent event) throws SQLException {
        try {
            if (uitgiftepuntenDrop11.getSelectionModel().getSelectedItem() != null) {
                MaxCapNaam();
                Lijst.updateList();
                updateComboBoxMax();

                maximumcapaciteitBewerken.setText("");
            }
        } catch (NumberFormatException e) {
            maximumcapaciteitBewerken.setText("");
            maximumcapaciteitBewerken.setPromptText("Typ een getal");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setHeaderText("Connectie is verbroken.");
            alert.setContentText("Controleer uw verbinding.");
        
            alert.showAndWait();
        }
    }

    //END FMXL
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AanPane.setEffect(addDropShadow(2, 2, Color.rgb(0, 0, 0, 0.2)));
        OpPane.setEffect(addDropShadow(0, 4, Color.rgb(0, 0, 0, 0.2)));
        BewPane.setEffect(addDropShadow(0, 4, Color.rgb(0, 0, 0, 0.2)));
        try {
            Lijst.updateList();
            updateComboBoxMax();
        } catch (SQLException ex) {
            Logger.getLogger(Uitgifte_SceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setHeaderText("Connectie is verbroken.");
            alert.setContentText("Controleer uw verbinding.");
        
            alert.showAndWait();
        }

    }

    private DropShadow addDropShadow(int offy, int offx, Color col) {
        DropShadow ds = new DropShadow();
        ds.setSpread(0);
        ds.setOffsetY(offy);
        ds.setOffsetX(offx);
        ds.setColor(col);
        return ds;
    }

    private boolean isStringCorrectSize(int length, String word) {
        return word.length() <= length;
    }

    private void MaxCapNaam() throws SQLException {
        String path = uitgiftepuntenDrop11.getSelectionModel().getSelectedItem();
        String segments[] = path.split(":");
        String naamSegement = segments[0];
        Statements.maximumCapaciteitAanpassen(Integer.parseInt(maximumcapaciteitBewerken.getText()), naamSegement);
    }

    private void updateComboBoxMax() throws SQLException {
        uitgiftepuntenDrop11.getItems().removeAll(observableList);
        Lijst.updateList();

        for (int i = 0; i < Lijst.uitgiftpuntenList.size(); i++) {
            observableList.add(i, Lijst.uitgiftpuntenList.get(i).getNaam() + ": " + Lijst.uitgiftpuntenList.get(i).getMaximumCapaciteit());
        }
        uitgiftepuntenDrop11.setItems(observableList);
    }
}
