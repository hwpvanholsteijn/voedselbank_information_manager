/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.dataGatherers;

import dataModels.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import utils.*;
import io.*;


/**
 *
 * @author hwpva
 */
public class IntakeStatusLijstReader {

    private IntakeStatusLijst intakeLijst;
    private ExcelTableInterface tabelReader;

    public IntakeStatusLijstReader(String filePath) {
        this.intakeLijst = new IntakeStatusLijst();
        try {
            ExtensieBepaler.checkBestaanFile(filePath);
            this.werkbookAanmaken(filePath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IntakeStatusLijstReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
        }

        this.verzamelIntakeGegevens();
    }

    private void werkbookAanmaken(String filePath) throws UnsupportedOperationException {
        switch (ExtensieBepaler.getFileExtensie(filePath)) {
            case "xlsx":
                this.tabelReader = new XSSFTabelReader(filePath);
                break;
            case "xls":
                this.tabelReader = new HSSFTabelReader(filePath);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void verzamelIntakeGegevens() {
        //Test for checking if all the arrays are complete
        ArrayList<String> beschikbareKolomnamen = this.tabelReader.getBeschikbareKolommmen();
        while (this.tabelReader.volgendeRijBeschikbaar()) {
            Row intakeRij = this.tabelReader.getVolgendeRij();
            this.leesRowGegevens(intakeRij, beschikbareKolomnamen);
        }
    }

    public void leesRowGegevens(Row rij, ArrayList<String> kolomIndexen) {
        Iterator<Cell> cellIterator = rij.cellIterator();
        int kolomIndex = 0;

        Client nieuweClient = new Client();
        ContactPersoon nieuwContactPersoon = new ContactPersoon();
        ContactPersoon tweedeContactPersoon = new ContactPersoon(true);
        Intake intakeGegevens = new Intake();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            this.clientGegevensVullen(nieuweClient, cell, kolomIndexen, kolomIndex);
            this.contactpersoonGegevensVullen(nieuwContactPersoon, cell, kolomIndexen, kolomIndex);
            this.contactpersoonGegevensVullen(tweedeContactPersoon, cell, kolomIndexen, kolomIndex);
            this.intakeGegevensVullen(intakeGegevens, cell, kolomIndexen, kolomIndex);

            kolomIndex++;
        }
        this.intakeLijst.clientToevoegen(nieuweClient);
        this.intakeLijst.contactPersoonoevoegen(nieuwContactPersoon);
        if (!tweedeContactPersoon.getInstantie().equals("") || !tweedeContactPersoon.getNaam().equals("") || !tweedeContactPersoon.getEmail().equals("")) {
            this.intakeLijst.tweedeContactPersoonoevoegen(tweedeContactPersoon);
        } else {
            this.intakeLijst.tweedeContactPersoonoevoegen(null);
        }

        this.intakeLijst.intakeToevoegen(intakeGegevens);
    }

    public void clientGegevensVullen(Client client, Cell cel, ArrayList<String> beschikbareKolommen, int kolomIndex) {
        switch (beschikbareKolommen.get(kolomIndex)) {
            case "kaartnummer":
                client.setKaartnummer(String.valueOf((int) cel.getNumericCellValue()));
                break;
            case "naam":
                client.setNaam(cel.getStringCellValue());
                break;
            case "naam partner":
                client.setPartnerNaam(cel.getStringCellValue());
                break;
            case "aantal personen":
                client.setAantalPersonen((int) cel.getNumericCellValue());
                break;
            case "adres":
                client.setAdres(cel.getStringCellValue());
                break;
            case "postcode":
                client.setPostcode(cel.getStringCellValue());
                break;
            case "plaats":
                client.setPlaats(cel.getStringCellValue());
                break;
            case "telefoonnummer":
                client.setTelefoonnummer(cel.getStringCellValue());
                break;
            case "mobiel / gsm":
                client.setMobieleTelefoonnummer(cel.getStringCellValue());
                break;
            case "e-mail":
                client.setEmail(cel.getStringCellValue());
                break;
            case "aantal personen in de norm":
                client.setAantalPersonenInNorn((int) cel.getNumericCellValue());
                break;

        }
    }

    public void contactpersoonGegevensVullen(ContactPersoon contactpersoon, Cell cel, ArrayList<String> beschikbareKolommen, int kolomIndex) {
        if (!contactpersoon.isTweedeVerwijzer()) {
            switch (beschikbareKolommen.get(kolomIndex)) {
                case "verwijzers door":
                    contactpersoon.setInstantie(cel.getStringCellValue());
                    break;
                case "verwijzers door contactpersoon":
                    contactpersoon.setNaam(cel.getStringCellValue());
                    break;
                case "verwijzers door telefoonnummer":
                    contactpersoon.setTelefoonnummer(cel.getStringCellValue());
                    break;
                case "verwijzers door email":
                    contactpersoon.setEmail(cel.getStringCellValue());
                    break;

            }
        } else {
            switch (beschikbareKolommen.get(kolomIndex)) {
                case "verwijzers naar":
                    contactpersoon.setInstantie(cel.getStringCellValue());
                    break;
                case "verwijzers naar contactpersoon":
                    contactpersoon.setNaam(cel.getStringCellValue());
                    break;
                case "verwijzers naar telefoonnummer":
                    contactpersoon.setTelefoonnummer(cel.getStringCellValue());
                    break;
                case "verwijzers naar email":
                    contactpersoon.setEmail(cel.getStringCellValue());
                    break;
            }
        }
    }

    public void intakeGegevensVullen(Intake intakeGev, Cell cel, ArrayList<String> beschikbareKolommen, int kolomIndex) {

        switch (beschikbareKolommen.get(kolomIndex)) {
            case "status":
                intakeGev.setStatus(cel.getStringCellValue());
                break;
            case "intakedatum":
                intakeGev.setIntakeDatum(LocalDateMaker.getDateFromCell(cel));
                break;
            case "startdatum uitgifte":
                intakeGev.setStartDatumUitgifte(LocalDateMaker.getDateFromCell(cel));
                break;
            case "datum herintake":
                intakeGev.setDatumHerintake(LocalDateMaker.getDateFromCell(cel));
                break;
            case "datum stopzetting":
                intakeGev.setDatumStopZetting(LocalDateMaker.getDateFromCell(cel));
                break;
            case "reden stopzettinge":
                intakeGev.setStatus(cel.getStringCellValue());
                break;
            case "pakket":
                intakeGev.setPakketGroottePerClient(cel.getStringCellValue());
                break;
            case "uitgiftepunt":
                intakeGev.setUitgiftepunt(cel.getStringCellValue());
                break;
            case "intaker":
                intakeGev.setMedewerker(cel.getStringCellValue());
                System.out.println(cel.getStringCellValue());
        }
    }

    /**
     * @return the intakeLijst
     */
    public IntakeStatusLijst getIntakeLijst() {
        return intakeLijst;
    }

}
