/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author hwpva
 */
public class XSSFTabelReader extends ExcelTabelReader implements ExcelTableInterface {

    private XSSFWorkbook excelWorkbook;

    public XSSFTabelReader(String filePath) {
        super();
        try {
            this.bufferExcel(filePath);
            super.setRijenOpslag(this.getGelecteerdSheet().iterator());
        } catch (IOException ex) {
            Logger.getLogger(XSSFTabelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public XSSFTabelReader(String filePath, int sheetIndex) {
        super();
        try {
            super.setSheetIndex(sheetIndex);
            System.out.println(super.getSheetIndex());
            this.bufferExcel(filePath);
            super.setRijenOpslag(this.getGelecteerdSheet().iterator());
        } catch (IOException ex) {
            Logger.getLogger(XSSFTabelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void bufferExcel(String filePath) throws IOException {
        FileInputStream exlFile = new FileInputStream(new File(filePath));
        excelWorkbook = new XSSFWorkbook(exlFile);
        exlFile.close();
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
    public XSSFSheet getGelecteerdSheet() {
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
