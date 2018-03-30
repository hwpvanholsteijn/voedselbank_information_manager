/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author hwpva
 */
public class ExcelTabelReader {

    private int sheetIndex = 0;
    private int kolomRij = 5;
    private ArrayList<String> kolomNamen;
    private Iterator<Row> rijenOpslag;

    public ExcelTabelReader() {
        this.kolomNamen = new ArrayList<>();
    }

    /**
     * @return the sheetIndex
     */
    public int getSheetIndex() {
        return sheetIndex;
    }

    /**
     * @param sheetIndex the sheetIndex to set
     */
    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    /**
     * @return the kolomRij
     */
    public int getKolomRij() {
        return kolomRij;
    }

    /**
     * @param kolomRij the kolomRij to set
     */
    public void setKolomRij(int kolomRij) {
        this.kolomRij = kolomRij;
    }

    /**
     * @return the kolomNamen
     */
    protected ArrayList<String> getKolomNamen() {
        this.gatherKolomnamen();
        return kolomNamen;
    }

    public void gatherKolomnamen() {
        Iterator<Row> ds = this.getRijenOpslag();
        int i = 0;
        while (ds.hasNext() && i < this.kolomRij) {
            Row rowMetKolomnamen = ds.next();
            if ((i + 1) == this.kolomRij) {
                Iterator<Cell> cellIterator = rowMetKolomnamen.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            this.kolomNamen.add(cell.getStringCellValue().toLowerCase());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            this.kolomNamen.add(this.getStringFromCellFormula(cell).toLowerCase());
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            this.kolomNamen.add("");
                            break;
                    }
                }
            }
            i++;
        }

    }

    private String getStringFromCellFormula(Cell cell) {
        String value = "";
        switch (cell.getCachedFormulaResultType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
        }
        return value;
        //source: https://stackoverflow.com/questions/7608511/java-poi-how-to-read-excel-cell-value-and-not-the-formula-computing-it
    }

    /**
     * @return the rijenOpslag
     */
    public Iterator<Row> getRijenOpslag() {
        return rijenOpslag;
    }

    /**
     * @param rijenOpslag the rijenOpslag to set
     */
    public void setRijenOpslag(Iterator<Row> rijenOpslag) {
        this.rijenOpslag = rijenOpslag;
    }

}
