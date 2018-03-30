package Lijsten;

import Filters.ClientFilter;
import dataModels.Client;
import dataModels.Medewerker;
import dataModels.Uitgiftepunt;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafxapplication6.FXMLDocumentController;
import javafxapplication6.Lijst_SceneController;

public class Statements {

    private static CallableStatement callableStatement;

    public static void uitgiftpuntToevoegen(String naam, String postcode, String adres, int maximumCapaciteit) throws SQLException {
        callableStatement = FXMLDocumentController.conn.prepareCall("{call uitgiftepuntToevoegen(?,?,?,?)}");
        callableStatement.setString(1, naam);
        callableStatement.setInt(2, maximumCapaciteit);
        callableStatement.setString(3, adres);
        callableStatement.setString(4, postcode);
        callableStatement.execute();
    }

    public static void clientToevoegen(
            int kaartnummer,
            String naam,
            String partner,
            int aantalPersonen,
            String adres,
            String postcode,
            String plaats,
            String telefoonnummer,
            String mobieleTelefoonnummer,
            String email,
            String contactPersoon) throws SQLException {
        callableStatement = FXMLDocumentController.conn.prepareCall("{call clientToevoegen(?,?,?,?,?,?,?,?,?,?,?)}");
        callableStatement.setInt(1, kaartnummer);
        callableStatement.setString(2, naam);
        callableStatement.setString(3, partner);
        callableStatement.setInt(4, aantalPersonen);
        callableStatement.setString(5, adres);
        callableStatement.setString(6, postcode);
        callableStatement.setString(7, plaats);
        callableStatement.setString(8, telefoonnummer);
        callableStatement.setString(9, mobieleTelefoonnummer);
        callableStatement.setString(10, email);
        callableStatement.setString(11, contactPersoon);
        callableStatement.execute();
    }
    
    public static void medewerker( String naam) throws SQLException{
        callableStatement = FXMLDocumentController.conn.prepareCall("{call medewerkerToevoegen(?)}");
        callableStatement.setString(1, naam);
        callableStatement.execute();
    }

    
    public static void intakeToevoegen(
            String kaartnummer,
            String medewerker,
            String datum,
            String status,
            String startdatumUitgifte,
            String stopdatumUitgifte,
            String datumHerintake,
            int pakketAantal,
            String uitgiftepunt
    ) throws SQLException {
        callableStatement = FXMLDocumentController.conn.prepareCall("{call intakeToevoegen(?,?,?,?,?,?,?,?,?)}");
        callableStatement.setString(1, kaartnummer);
        callableStatement.setString(2, status);
        callableStatement.setString(3, datum);
        callableStatement.setString(4, startdatumUitgifte);
        callableStatement.setString(5, stopdatumUitgifte);
        callableStatement.setString(6, datumHerintake);
        callableStatement.setInt(7, pakketAantal);
        callableStatement.setString(8, medewerker);
        callableStatement.setString(9, uitgiftepunt);
        callableStatement.execute();
    }

    public static void contactPersoonToevoegen(String naam, String instantie, String telefoonnummer, String email) throws SQLException {
        callableStatement = FXMLDocumentController.conn.prepareCall("{call contactPersoonToevoegen(?,?,?,?)}");
        callableStatement.setString(1, naam);
        callableStatement.setString(2, instantie);
        callableStatement.setString(3, telefoonnummer);
        callableStatement.setString(4, email);
        callableStatement.execute();
    }

    public static void maximumCapaciteitAanpassen(int maxC, String naam) throws SQLException {
        callableStatement = FXMLDocumentController.conn.prepareCall("{call maxCapaciteitAanpassen(?,?)}");
        callableStatement.setInt(1, maxC);
        callableStatement.setString(2, naam);
        callableStatement.execute();
    }

    public static ArrayList<Uitgiftepunt> getAllUitgiftepunt() throws SQLException {
        ArrayList<Uitgiftepunt> list = new ArrayList<>();
        callableStatement = FXMLDocumentController.conn.prepareCall("{call getAllUitgiftepunten(?)}");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String datum;
        
        if(Lijst_SceneController.datumString == null){
            datum = dtf.format(localDate);
        } else{
            datum = Lijst_SceneController.datumString;
        }
        
        
        callableStatement.setString(1, datum); 
        callableStatement.execute();
        ResultSet rs = callableStatement.getResultSet();

        
        while (rs.next()) {
            Uitgiftepunt tijdelijkRow = convertRowToUitgiftepunt(rs);
            list.add(tijdelijkRow);
        }
        return list;
    }
    
    public static ArrayList<Uitgiftepunt> getAllUitgiftepunt2() throws SQLException {
        ArrayList<Uitgiftepunt> list = new ArrayList<>();
        callableStatement = FXMLDocumentController.conn.prepareCall("{call getAllUitgiftepunten(?)}");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String datum;

        if(Lijst_SceneController.datumString == null){
            datum = dtf.format(localDate);
            System.out.println("up");
        } else{
            datum = Lijst_SceneController.datumString;
            System.out.println("down");
        }
        
        
        callableStatement.setString(1, datum); 
        callableStatement.execute();
        ResultSet rs = callableStatement.getResultSet();

        while (rs.next()) {
            Uitgiftepunt tijdelijkRow = convertRowToUitgiftepunt2(rs);
            list.add(tijdelijkRow);
        }
        return list;
    }
    
    public static ArrayList<Client> getAllClient(String status) throws SQLException {
        ArrayList<Client> list = new ArrayList<>();
        callableStatement = FXMLDocumentController.conn.prepareCall("{call getAllClient(?,?)}");
        
        if("Geen voorkeur".equals(ClientFilter.plaatsVoorkeur)){
            ClientFilter.plaatsVoorkeur = "%";
        }
        
        if("Geen voorkeur".equals(ClientFilter.statusVoorkeur)){
            ClientFilter.statusVoorkeur = "%";
        }
        
        
        callableStatement.setString(1, ClientFilter.plaatsVoorkeur);
        callableStatement.setString(2, ClientFilter.statusVoorkeur);
        callableStatement.execute();
        
        ResultSet rs = callableStatement.getResultSet();
        while (rs.next()) {
            Client tijdelijkRow = convertRowToClient(rs);
            list.add(tijdelijkRow);
        }
        return list;
    }
    
    public static ArrayList<Client> getAllClientPlaats() throws SQLException {
        ArrayList<Client> list = new ArrayList<>();
        callableStatement = FXMLDocumentController.conn.prepareCall("{call getAllPlaats()}");
        callableStatement.execute();
        ResultSet rs = callableStatement.getResultSet();

        while (rs.next()) {
            Client tijdelijkRow = convertRowToClientPlaats(rs);
            list.add(tijdelijkRow);
        }
        return list;
    }
    
    public static ArrayList<Medewerker> getAllMedewerkers() throws SQLException {
        ArrayList<Medewerker> list = new ArrayList<>();
        callableStatement = FXMLDocumentController.conn.prepareCall("{call getAllMedewerkers()}");
        callableStatement.execute();
        ResultSet rs = callableStatement.getResultSet();

        while (rs.next()) {
            Medewerker tijdelijkRow = convertRowToMedewerker(rs);
            list.add(tijdelijkRow);
        }
        return list;
    }
    
    public static ResultSet getAllUitgiftepuntResultSet() throws SQLException {
        ArrayList<Uitgiftepunt> list = new ArrayList<>();
        callableStatement = FXMLDocumentController.conn.prepareCall("{call getAllUitgiftenpunten(?)}");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String datum;
        
        if(Lijst_SceneController.datumString == null){
            datum = dtf.format(localDate);
            System.out.println("up");
        } else{
            datum = Lijst_SceneController.datumString;
            System.out.println("down");
        }
        
        
        callableStatement.setString(1, datum); 
        
        callableStatement.execute();
        ResultSet rs = callableStatement.getResultSet();
        return rs;
    }
        
    public static String getNaamGebruiker(String login,String Wachtwoord) throws SQLException {
        String gebruiker = null;
        callableStatement = FXMLDocumentController.conn.prepareCall("{call getNaamGebruiker(?,?)}");
        callableStatement.setString(1, login);
        callableStatement.setString(2, Wachtwoord);
        callableStatement.execute();
        ResultSet rs = callableStatement.getResultSet();
        
        while (rs.next()) {
            gebruiker = rs.getString("Naam");
        }
        return gebruiker;
    }

    private static Uitgiftepunt convertRowToUitgiftepunt(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        String naam = rs.getString("Naam");
        int capaciteit = rs.getInt("MaximumCapaciteit");
        int aantalCliënten = rs.getInt("AantalClienten");
        String adres = rs.getString("Adres");
        String postcode = rs.getString("Postcode");
        int pakketAantal = rs.getInt("pakketAantal");

        Uitgiftepunt tijdelijkeHoofdtabel = new Uitgiftepunt(id, naam, capaciteit, aantalCliënten, postcode, adres, pakketAantal);
        return tijdelijkeHoofdtabel;
    }
    
    private static Uitgiftepunt convertRowToUitgiftepunt2(ResultSet rs) throws SQLException {
        String naam = rs.getString("Naam");
        String adres = rs.getString("Adres");
        String postcode = rs.getString("Postcode");
        String plaats = rs.getString("Plaats");
        int enkelPakket = rs.getInt("Enkel pakket");
        int dubblePakket = rs.getInt("Dubble pakket");
        int triplePakket = rs.getInt("3-voudig pakket");
        int pakketAantal = rs.getInt("pakketAantal");

        Uitgiftepunt tijdelijkeHoofdtabel = new Uitgiftepunt(naam, postcode, adres, plaats, pakketAantal, enkelPakket, dubblePakket, triplePakket);
        return tijdelijkeHoofdtabel;
    }
    
    private static Client convertRowToClient(ResultSet rs) throws SQLException {
     String kaartnummer = rs.getString("Kaartnummer");
     String naam = rs.getString("Naam");
     String adres = rs.getString("Adres");
     String postcode = rs.getString("Postcode");
     String plaats = rs.getString("Plaats");
     String telefoonnummer = rs.getString("Telefoonnummer");
     String mobieleTelefoonnummer = rs.getString("MobieleTelefoonnummer");
     String Email =rs.getString("Email");
     String Uitgiftepunt = rs.getString("Uitgiftepunt");
     String Contactpersoon = rs.getString("Contactpersoon");
     int aantalPakketen = rs.getInt("PakketAantal");
     String status = rs.getString("Status");
     String startDatum = rs.getString("StartdatumUitgifte");
     String stopDatum = rs.getString("StopdatumUitgifte");
     String herintakeDatum = rs.getString("DatumHerintake");
     int aantalPeronen = rs.getInt("AantalPersonen");

        Client tijdelijkeHoofdtabel = new Client(kaartnummer, naam, adres, postcode, plaats, telefoonnummer, mobieleTelefoonnummer, Email, Uitgiftepunt, Contactpersoon, aantalPakketen, status, startDatum, stopDatum, herintakeDatum, aantalPeronen);
        return tijdelijkeHoofdtabel;
    }
    
    private static Client convertRowToClientPlaats(ResultSet rs) throws SQLException {

     String plaats = rs.getString("Plaats");


        Client tijdelijkeHoofdtabel = new Client("123", "!23", "!23", "!23", plaats, "123", "!23", "!23", "!@3", "123", 1, "123", "123", "!23", "123", 12);
        return tijdelijkeHoofdtabel;
    }
    
    private static Medewerker convertRowToMedewerker(ResultSet rs) throws SQLException {
        String naam = rs.getString("M_ID");
        Medewerker tijdelijkeHoofdtabel = new Medewerker(naam);
        return tijdelijkeHoofdtabel;
    }

    public static ArrayList<Uitgiftepunt> getAllUitgiftepuntNaam() throws SQLException {
        ArrayList<Uitgiftepunt> list = new ArrayList<>();
        callableStatement = FXMLDocumentController.conn.prepareCall("{call getAllUitgiftepuntenNaam()}");
        callableStatement.execute();
        ResultSet rs = callableStatement.getResultSet();

        while (rs.next()) {
            Uitgiftepunt tijdelijkRow = convertRowToUitgiftepunt(rs);
            list.add(tijdelijkRow);
        }
        return list;
    }
}
