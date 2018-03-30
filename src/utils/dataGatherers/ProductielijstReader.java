/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.dataGatherers;

import dataModels.Productielijst;
import io.ExtensieBepaler;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import utils.*;


/**
 *
 * @author hwpva
 */
public class ProductielijstReader {

    private String filePath;
    private Productielijst productielijst;
    private ExcelTableInterface tabelReader;

    public ProductielijstReader(String filePath) {
        this.productielijst = new Productielijst();
        this.filePath = filePath;
    }

    public ProductielijstReader() {
        this.productielijst = new Productielijst();
    }

    public void startLezen() {
        try {
            ExtensieBepaler.checkBestaanFile(this.getFilePath());
            this.werkbookAanmaken(this.getFilePath());
            this.verzamelProductielijstGegevens();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IntakeStatusLijstReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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

    private void verzamelProductielijstGegevens() {
        //Test for checking if all the arrays are complete

        this.tabelReader.setGeselecteerdeSheet(1);
        this.tabelReader.setEersteKolomRijExcel(17);

        
        
        ArrayList<String> beschikbareKolomnamen = this.tabelReader.getBeschikbareKolommmen();
        System.out.println(beschikbareKolomnamen.toString());
        this.printArray(beschikbareKolomnamen);
        while (this.tabelReader.volgendeRijBeschikbaar()) {
            Row intakeRij = this.tabelReader.getVolgendeRij();
            this.leesRowGegevens(intakeRij, beschikbareKolomnamen);
        }
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private void leesRowGegevens(Row intakeRij, ArrayList<String> beschikbareKolomnamen) {
        

//        while (this.tabelReader.volgendeRijBeschikbaar()) {
//            Row intakeRij = this.tabelReader.getVolgendeRij();
//            this.leesRowGegevens(intakeRij, beschikbareKolomnamen);
//        }
    }

    private void printArray(ArrayList d) {
        int index =0;
        for (Object a : d) {
            System.out.println(index+": "+ a);
            index++;
        }
    }

}
