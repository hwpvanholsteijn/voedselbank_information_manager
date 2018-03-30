package Lijsten;

import Filters.ClientFilter;
import static Lijsten.Lijst.uitgiftpuntenList;
import dataModels.Client;
import dataModels.Medewerker;
import dataModels.Uitgiftepunt;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.dataGatherers.IntakeStatusLijstReader;

public class Lijst {

    public static ArrayList<Uitgiftepunt> uitgiftpuntenList = new ArrayList<Uitgiftepunt>();
    public static ObservableList<Uitgiftepunt> uitgiftpuntenObservableList;
    
    public static ArrayList<Uitgiftepunt> bevoorradingslijstList = new ArrayList<Uitgiftepunt>();
    public static ObservableList<Uitgiftepunt> bevoorradingsObservableList;
    
    public static ArrayList<Client> clientenList = new ArrayList<Client>();
    public static ObservableList<Client> clientObservableList;
    
    public static ArrayList<Client> clientenListPlaats = new ArrayList<Client>();

    public static void updateList() throws SQLException {
        clientenListPlaats = Statements.getAllClientPlaats();
        
        uitgiftpuntenList = Statements.getAllUitgiftepunt();
        uitgiftpuntenObservableList = FXCollections.observableList(uitgiftpuntenList);
        
        bevoorradingslijstList = Statements.getAllUitgiftepunt2();
        bevoorradingsObservableList = FXCollections.observableList(bevoorradingslijstList);
        
        clientenList = Statements.getAllClient(ClientFilter.statusVoorkeur);
        clientObservableList = FXCollections.observableList(clientenList);
    }
    
    public static void clientDatabaseUpdate() {
        int kaartnummer;
        String naam;
        String partner;
        int aantalPersonen;
        String adres;
        String postcode;
        String plaats;
        String telefoonnummer;
        String mobieleTelefoonnummer;
        String email;
        String contactPersoon;

        IntakeStatusLijstReader prodReader = new IntakeStatusLijstReader("./assets/samples_docs/excel/intake_statuslijst.xls");

        for (int i = 0; i < prodReader.getIntakeLijst().getClienten().size(); i++) {
            kaartnummer = Integer.parseInt(prodReader.getIntakeLijst().getClienten().get(i).getKaartnummer());
            naam = prodReader.getIntakeLijst().getClienten().get(i).getNaam();
            partner = prodReader.getIntakeLijst().getClienten().get(i).getPartnerNaam();
            aantalPersonen = prodReader.getIntakeLijst().getClienten().get(i).getAantalPersonen();
            adres = prodReader.getIntakeLijst().getClienten().get(i).getAdres();
            postcode = prodReader.getIntakeLijst().getClienten().get(i).getPostcode();
            plaats = prodReader.getIntakeLijst().getClienten().get(i).getPlaats();
            telefoonnummer = prodReader.getIntakeLijst().getClienten().get(i).getTelefoonnummer();
            mobieleTelefoonnummer = prodReader.getIntakeLijst().getClienten().get(i).getMobieleTelefoonnummer();
            email = prodReader.getIntakeLijst().getClienten().get(i).getEmail();
            contactPersoon = prodReader.getIntakeLijst().getContactPersonen().get(i).getNaam();

            try {
                Statements.clientToevoegen(kaartnummer, naam, partner, aantalPersonen, adres, postcode, plaats, telefoonnummer, mobieleTelefoonnummer, email, contactPersoon);
            } catch (SQLException ex) {
                Logger.getLogger(Lijst.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void contactPersonenDatabaseUpdate() {
        String naam;
        String instantie;
        String telefoonnummer;
        String email;

        IntakeStatusLijstReader prodReader = new IntakeStatusLijstReader("./assets/samples_docs/excel/intake_statuslijst.xls");

        for (int i = 0; i < prodReader.getIntakeLijst().getContactPersonen().size(); i++) {

            naam = prodReader.getIntakeLijst().getContactPersonen().get(i).getNaam();
            instantie = prodReader.getIntakeLijst().getContactPersonen().get(i).getInstantie();
            email = prodReader.getIntakeLijst().getContactPersonen().get(i).getEmail();
            telefoonnummer = prodReader.getIntakeLijst().getContactPersonen().get(i).getTelefoonnummer();
            try {
                Statements.contactPersoonToevoegen(naam, instantie, telefoonnummer, email);
            } catch (SQLException ex) {
                Logger.getLogger(Lijst.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void IntakeDatabaseUpdate() {
        IntakeStatusLijstReader prodReader = new IntakeStatusLijstReader("./assets/samples_docs/excel/intake_statuslijst.xls");

        String kaartnummer;
        String medewerker;
        String datum;
        String status;
        String startdatumUitgifte;
        String stopdatumUitgifte;
        String datumHerintake;
        int pakketAantal;
        String uitgiftepunt;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        datum = dtf.format(localDate);

        for (int i = 0; i < prodReader.getIntakeLijst().getIntakes().size(); i++) {
            kaartnummer = prodReader.getIntakeLijst().getClienten().get(i).getKaartnummer();
            medewerker = prodReader.getIntakeLijst().getIntakes().get(i).getMedewerker();
            startdatumUitgifte = null;
            stopdatumUitgifte = null;
            datumHerintake = null;

            if (prodReader.getIntakeLijst().getIntakes().get(i).getStartDatumUitgifte() != null) {
                startdatumUitgifte = prodReader.getIntakeLijst().getIntakes().get(i).getStartDatumUitgifte().toString();
            }

            if (prodReader.getIntakeLijst().getIntakes().get(i).getDatumStopZetting() != null) {
                stopdatumUitgifte = prodReader.getIntakeLijst().getIntakes().get(i).getDatumStopZetting().toString();
            }

            if (prodReader.getIntakeLijst().getIntakes().get(i).getDatumHerintake() != null) {
                datumHerintake = prodReader.getIntakeLijst().getIntakes().get(i).getDatumHerintake().toString();
            }

            status = prodReader.getIntakeLijst().getIntakes().get(i).getStatus();
            pakketAantal = prodReader.getIntakeLijst().getIntakes().get(i).getPakketGroottePerClient();
            uitgiftepunt = prodReader.getIntakeLijst().getIntakes().get(i).getUitgiftepunt();

            try {
                Statements.intakeToevoegen(kaartnummer, medewerker, datum, status, startdatumUitgifte, stopdatumUitgifte, datumHerintake, pakketAantal, uitgiftepunt);
            } catch (SQLException ex) {
                Logger.getLogger(Lijst.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    
    public static void medewerkerUpdate(){
        String naamString;
        ArrayList<Medewerker> list = null;
        try {
            list = Statements.getAllMedewerkers();
        } catch (SQLException ex) {
            Logger.getLogger(Lijst.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < list.size(); i++) {
            
         naamString = list.get(i).getNaam();
         
            try {
                Statements.medewerker(naamString);
            } catch (SQLException ex) {
                Logger.getLogger(Lijst.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }  
    }
}
