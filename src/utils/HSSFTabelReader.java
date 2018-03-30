/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author hwpva
 */
public class HSSFTabelReader extends ExcelTabelReader implements ExcelTableInterface {

    private HSSFWorkbook excelWorkbook;

    public HSSFTabelReader(String filePath) {
        super();
        try {
            this.bufferExcel(filePath);
            super.setRijenOpslag(this.getGelecteerdSheet().iterator());
        } catch (IOException ex) {
            Logger.getLogger(HSSFTabelReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    private void bufferExcel(String filePath) throws IOException {
        FileInputStream exlFile = new FileInputStream(new File(filePath));
        excelWorkbook = new HSSFWorkbook(exlFile);
        if (exlFile != null) {
            exlFile.close();
        }
    }

    @Override
    public boolean volgendeRijBeschikbaar() {
        return super.getRijenOpslag().hasNext();
    }

    @Override
    public Row getVolgendeRij() {
        return super.getRijenOpslag().next();
    }

    @Override
    public HSSFSheet getGelecteerdSheet() {
        return this.excelWorkbook.getSheetAt(super.getSheetIndex());
    }

    @Override
    public ArrayList<String> getBeschikbareKolommmen() {
        return super.getKolomNamen();
    }

    @Override
    public void setEersteKolomRijExcel(int indexKolomRij) {
        super.setKolomRij(indexKolomRij);
    }

    @Override
    public void setGeselecteerdeSheet(int sheetsIndex) {
        super.setSheetIndex(sheetsIndex);
        super.setRijenOpslag(this.getGelecteerdSheet().iterator());
    }
   
    

}
