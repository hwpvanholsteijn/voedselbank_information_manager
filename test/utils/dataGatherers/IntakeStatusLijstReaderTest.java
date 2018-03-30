/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.dataGatherers;

import dataModels.Client;
import dataModels.ContactPersoon;
import dataModels.Intake;
import dataModels.IntakeStatusLijst;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author mustafa
 */
public class IntakeStatusLijstReaderTest {
    
    private IntakeStatusLijstReader newExcelReader;
    private IntakeStatusLijstReader oldExcelReader;

    public IntakeStatusLijstReaderTest() {
        this.newExcelReader = new IntakeStatusLijstReader("./assets/samples_docs/excel/intake_statuslijst.xlsx");
        this.oldExcelReader = new IntakeStatusLijstReader("./assets/samples_docs/excel/intake_statuslijst.xls");
    }

    /**
     * Test of leesRowGegevens method, of class IntakeStatusLijstReader.
     */
    @Test
    public void geenFileInputExcelReader() {
        System.out.println("Geen fileput in de excelreader");
        IntakeStatusLijstReader noFileInputReader = new IntakeStatusLijstReader("");
        IntakeStatusLijst intake = noFileInputReader.getIntakeLijst();
        Assert.assertEquals("Clienten heeft uitgelezen gegevens.", 0, intake.getClienten().size());
        Assert.assertEquals("Contactpersonen heeft uitgelezen gegevens.", 0, intake.getContactPersonen().size());
        Assert.assertEquals("Intakestatuslijst heeft uitgelezen gegevens.", 0, intake.getIntakes().size());
        Assert.assertEquals("Twee contactpersoon heeft uitgelezen gegevens.", 0, intake.getTweedeContactpersoon().size());
    }

    /**
     * Test of leesRowGegevens method, of class IntakeStatusLijstReader.
     */
    @Test
    public void clientGegevensInlezen() {
        System.out.println("clienten gegevens inlezen");
        IntakeStatusLijst intkLijst = newExcelReader.getIntakeLijst();
        ArrayList<Client> clientenLijst = intkLijst.getClienten();

        if (clientenLijst.size() > 0) {
            clientenLijst.forEach((client) -> {
                Assert.assertNotNull("Er zitten null variabelen in clienten", client);
            });

            Client eersteClientIntake = clientenLijst.get(0);
            Client voorbeeldClient = new Client();
            voorbeeldClient.setKaartnummer("54225");
            voorbeeldClient.setNaam("Persoon1");
            voorbeeldClient.setPartnerNaam("");
            voorbeeldClient.setTelefoonnummer("");
            voorbeeldClient.setEmail("");
            voorbeeldClient.setMobieleTelefoonnummer("");
            voorbeeldClient.setAantalPersonen(4);
            voorbeeldClient.setAantalPersonenInNorn(4);
            voorbeeldClient.setAdres("Adres1");
            voorbeeldClient.setPostcode("2512ST");

            this.testClients(eersteClientIntake, voorbeeldClient);
        } else {
            fail("Er zijn geen clienten om te kunnen beoordelen");
        }
    }

    @Test
    public void checkOudeExcelEnNieuweCompatibiliteit() {
        System.out.println("excel lees comptibiliteit check");
        IntakeStatusLijst intkLijst = this.newExcelReader.getIntakeLijst();
        ArrayList<Client> clientenLijst = intkLijst.getClienten();

        IntakeStatusLijst r = this.oldExcelReader.getIntakeLijst();
        ArrayList<Client> oudeClientenLijst = r.getClienten();

        if (clientenLijst.size() > 0 && oudeClientenLijst.size() > 0) {
            clientenLijst.forEach((client) -> {
                Assert.assertNotNull("Er zitten null variabelen in nieuwe clientenlijst", client);
            });

            oudeClientenLijst.forEach((client) -> {
                Assert.assertNotNull("Er zitten null variabelen in oude clientenlijst", client);
            });

            this.testClients(clientenLijst.get(0), oudeClientenLijst.get(0));
        } else {
            fail("Er zijn geen clienten om te kunnen beoordelen");
        }
    }

    public void testClients(Client eersteClientIntake, Client voorbeeldClient) {
        Assert.assertEquals("Kaartnummer is niet gelijk aan elkaar", eersteClientIntake.getKaartnummer(), voorbeeldClient.getKaartnummer());
        Assert.assertEquals("Naam is niet gelijk aan elkaar", eersteClientIntake.getNaam(), voorbeeldClient.getNaam());
        Assert.assertEquals("partnernaam is niet gelijk aan elkaar", eersteClientIntake.getPartnerNaam(), voorbeeldClient.getPartnerNaam());
        Assert.assertEquals("telefoonnummer is niet gelijk aan elkaar", eersteClientIntake.getTelefoonnummer(), voorbeeldClient.getTelefoonnummer());
        Assert.assertEquals("email is niet gelijk aan elkaar", eersteClientIntake.getEmail(), voorbeeldClient.getEmail());
        Assert.assertEquals("mobiel telefoonnummer is niet gelijk aan elkaar", eersteClientIntake.getMobieleTelefoonnummer(), voorbeeldClient.getMobieleTelefoonnummer());
        Assert.assertEquals("Het aantal personen is niet gelijk aan elkaar", eersteClientIntake.getAantalPersonen(), voorbeeldClient.getAantalPersonen());
        Assert.assertEquals("Het aantal personen in de norm is niet gelijk aan elkaar", eersteClientIntake.getAantalPersonenInNorn(), voorbeeldClient.getAantalPersonenInNorn());
        Assert.assertEquals("adres is niet gelijk aan elkaar", eersteClientIntake.getAdres(), voorbeeldClient.getAdres());
        Assert.assertEquals("postcode is niet gelijk aan elkaar", eersteClientIntake.getPostcode(), voorbeeldClient.getPostcode());
    }
}
