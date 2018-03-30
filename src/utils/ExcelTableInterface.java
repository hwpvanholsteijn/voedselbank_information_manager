/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author hwpva
 */
public interface ExcelTableInterface {

    public boolean volgendeRijBeschikbaar();

    public Row getVolgendeRij();

    public void setEersteKolomRijExcel(int indexKolomRij);

    public void setGeselecteerdeSheet(int sheetsIndex);

    public Sheet getGelecteerdSheet();

    public ArrayList<String> getBeschikbareKolommmen();
}
