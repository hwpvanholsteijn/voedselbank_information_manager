package javafxapplication6;

import Lijsten.Lijst;
import Filters.UitgiftenpuntFilter;
import dataModels.Uitgiftepunt;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GegevensOpvragen_SceneController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        tableView.getColumns().clear();
        addColumns();
        tableView.setItems(Lijst.uitgiftpuntenObservableList);

        postcode.setCellValueFactory(
                new PropertyValueFactory<>("postcode"));

        uitgiftepunt.setCellValueFactory(
                new PropertyValueFactory<>("naam"));

        adres.setCellValueFactory(
                new PropertyValueFactory<>("adres"));

        maximumCapaciteit.setCellValueFactory(
                new PropertyValueFactory<>("maximumCapaciteit"));

        teLeverenPakketen.setCellValueFactory(
                new PropertyValueFactory<>("teLeverenPakketen"));

        aantalClienten.setCellValueFactory(
                new PropertyValueFactory<>("aantalCliënten"));

        teLeverenPakketen.setCellFactory(column -> {
            return new TableCell<Uitgiftepunt, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item.toString());
                        int plekOver = maximumCapaciteit.getCellData(getIndex()) - item;
                        if (plekOver <= 0) {
                            setStyle("-fx-background-color: red");
                        } else if (plekOver < 10) {
                            setStyle("-fx-background-color: orange");
                        }else{
                            setStyle("-fx-background-color: none");
                        }
                    }
                }
            };
        });

        maximumCapaciteit.setCellFactory(column -> {
            return new TableCell<Uitgiftepunt, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item.toString());
                        int plekOver = item - teLeverenPakketen.getCellData(getIndex());
                        if (plekOver <= 0) {
                            setStyle("-fx-background-color: red");
                        } else if (plekOver < 10) {
                            setStyle("-fx-background-color: orange");
                        } else{
                            setStyle("-fx-background-color: none");
                        }
                    }
                }
            };
        });
    }

    @FXML
    private Pane OpPane;

    @FXML
    private TableColumn<Uitgiftepunt, Integer> teLeverenPakketen;

    @FXML
    private TableColumn<Uitgiftepunt, Integer> aantalClienten;

    @FXML
    private TableColumn<Uitgiftepunt, String> postcode;

    @FXML
    private TableColumn<Uitgiftepunt, String> uitgiftepunt;

    @FXML
    private TableColumn<Uitgiftepunt, String> adres;

    @FXML
    private TableColumn<Uitgiftepunt, Integer> maximumCapaciteit;

    @FXML
    private TableView<Uitgiftepunt> tableView;

    @FXML
    private void backButton(MouseEvent event) throws IOException {
        HomePageController.sceneControl.setScene(1);
    }

    private void addColumns() {
        tableView.getColumns().add(uitgiftepunt);

        if (UitgiftenpuntFilter.aantalClienten) {
            tableView.getColumns().add(aantalClienten);
        }

        if (UitgiftenpuntFilter.capaciteit) {
            tableView.getColumns().add(maximumCapaciteit);
            tableView.getColumns().add(teLeverenPakketen);

        }
        if (UitgiftenpuntFilter.toonAdres) {
            tableView.getColumns().add(adres);
        }
        if (UitgiftenpuntFilter.toonPostcode) {
            tableView.getColumns().add(postcode);
        }
    }
}
