/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelWriter;

import dataModels.Uitgiftepunt;
import java.io.File;
import java.util.Date;
import java.util.List;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Harrie
 */

public class ProductielijstExcelTemplate implements Template {
    
    
    
    public static String fileOutputPath = "./files/Productielijst Voedselbank.xlsx";
    private final String titel = "Productielijst";
    private final String voedselbank = "Voedselbank Haaglanden";
    
    private Workbook workbook;
    
    private String[] columnName= new String[] {
            "Uitgiftepunt", "Enkelvoudig pakket", "Dubbel pakket", "3-voudig pakket", "Totaal"
        };
    
    private String[] totalName= new String[] {
            "3-voudig pakket:", "Dubbel pakket:", "Enkelvoudig pakket:", "Pakketten totaal:"
        };
    
    private List<Uitgiftepunt> data;
    
  

    public ProductielijstExcelTemplate(List<Uitgiftepunt> data) {
        this.data = data;
        System.out.println(data);
    }
    
    public void writeData() {
        
        workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(titel);
        sheet.getPrintSetup().setLandscape(true);
        
        // Layout
        
        CellStyle cs = workbook.createCellStyle();
        CellStyle cs2 = workbook.createCellStyle();
        CellStyle cs3 = workbook.createCellStyle();
        CellStyle cs4 = workbook.createCellStyle();
        CellStyle cs5 = workbook.createCellStyle();
        CellStyle greyBG = workbook.createCellStyle();
        
        CreationHelper createHelper = workbook.getCreationHelper();
        
        // create 2 fonts objects
        Font font1 = workbook.createFont();
        Font boldFont = workbook.createFont();
        
        // name list
        font1.setBold(true);
        font1.setFontHeightInPoints((short)20);
        
        // name columns
        boldFont.setBold(true);
        
        cs.setDataFormat(createHelper.createDataFormat().getFormat("dd-mm-yyyy"));
        cs.setAlignment(HorizontalAlignment.LEFT);

        cs2.setFont(boldFont);
        cs2.setFont(font1);
        
        cs3.setBorderBottom(BorderStyle.THIN);
        cs3.setBorderTop(BorderStyle.THIN);
        cs3.setFont(boldFont);
        
        cs4.setFont(boldFont);
        
        cs5.setBorderBottom(BorderStyle.THIN);
        cs5.setBorderTop(BorderStyle.THIN);
        cs5.setFont(boldFont);
        cs5.setAlignment(HorizontalAlignment.RIGHT);
        
        greyBG.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        greyBG.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        // Begin writing data
        createTopInfo(sheet, cs, cs2);
        createColumnName(sheet, cs3, cs5);
        fillData(sheet, greyBG, cs4);
    }

    private void createTopInfo(Sheet sheet, CellStyle cs, CellStyle cs2) {
        Row row = null;
        Cell cell = null;
        
        for (int i = 0; i < 3; i++) {
            row = sheet.createRow(i);
            cell = row.createCell(0);
            if (i == 0) {
                row.setHeightInPoints(30);
                cell.setCellStyle(cs2);
                cell.setCellValue(titel);
            } else if (i == 1) {
                cell.setCellValue("Voedselbank:");
                cell = row.createCell(2);
                cell.setCellValue(voedselbank);
            } else if (i == 2) {
                cell.setCellValue("Pijldatum:");
                cell = row.createCell(2);
                cell.setCellValue(new Date());
                cell.setCellStyle(cs);
            }
        }
        sheet.autoSizeColumn(2);
    }

    private void createColumnName(Sheet sheet, CellStyle cs3, CellStyle cs5) {
        Row row = null;
        Cell cell = null;
        
        row = sheet.createRow(4);
        
        int a = 0;
        int b = 0;
        while (b < columnName.length) {
            cell = row.createCell(a);
            if (a != 1 && a != 2 && a != 3 && a != 4) {
                cell.setCellValue(columnName[b++]);
            }
            if (a == 0) {
                cell.setCellStyle(cs3);
            } else {
                cell.setCellStyle(cs5);
            }
            a++;
        }
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        
    }

    private void fillData(Sheet sheet, CellStyle greyBG, CellStyle cs4) {
        Row row = null;
        Cell cell = null;
        
        int uitgiftepuntNum = 0;
        int totalEnkel = 0, totalDubbel = 0, total3voud = 0, totalPakket = 0;
        boolean makeGrey = false;
        int bottomRow = ((data.size() + 2) * 2) + 1;
        
        for (int i = 5; i < bottomRow; i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < columnName.length + 4; j++) {
                cell = row.createCell(j);
                if (i % 2 != 0 && j == 0) {
                    cell.setCellValue(data.get(uitgiftepuntNum).getNaam());
                } else if (i % 2 == 0 && j == 1) {
                    cell.setCellValue(data.get(uitgiftepuntNum).getAdres() + ", " 
                            + data.get(uitgiftepuntNum).getPostcode() + ", " 
                            + data.get(uitgiftepuntNum).getPlaats());
                } else if (i % 2 != 0 && j == 5) {
                    cell.setCellValue((int)data.get(uitgiftepuntNum).getEnkelvoudigPakket());
                    totalEnkel += (int)cell.getNumericCellValue();
                } else if (i % 2 != 0 && j == 6) {
                    cell.setCellValue((int)data.get(uitgiftepuntNum).getDubblePakket());
                    totalDubbel += (int)cell.getNumericCellValue();
                } else if (i % 2 != 0 && j == 7) {
                    cell.setCellValue((int)data.get(uitgiftepuntNum).getDrievoudigPakket());
                    total3voud += (int)cell.getNumericCellValue();
                } else if (i % 2 != 0 && j == 8) {
                    cell.setCellValue((int)data.get(uitgiftepuntNum).getTeLeverenPakketen());
                    totalPakket += (int)cell.getNumericCellValue();
                }
                if (makeGrey)
                    cell.setCellStyle(greyBG);
            }
            if (i % 2 == 0) {
                makeGrey = !makeGrey;
                uitgiftepuntNum++;
            }
        }
        
        createTotal(sheet, cs4, bottomRow, 
                total3voud, totalDubbel, totalEnkel, totalPakket);
        
    }
    
    private void createTotal(Sheet sheet, CellStyle cs4, int bottomRow, 
            int total3voud, int totalDubbel, int totalEnkel, int totalPakket) {
        Row row = null;
        Cell cell = null;
        
        // "3-voudig pakket:", "Dubbel pakket:", "Enkelvoudig pakket:", "Pakketten totaal:"
        for (int i = 0; i < totalName.length; i++) {
            row = sheet.createRow(++bottomRow);
            cell = row.createCell(0);
            cell.setCellValue(totalName[i]);
            cell.setCellStyle(cs4);
            cell = row.createCell(2);
            if (i == 0) {
                cell.setCellValue(total3voud);
            } else if (i == 1) {
                cell.setCellValue(totalDubbel);
            } else if (i == 2) {
                cell.setCellValue(totalEnkel);
            } else if (i == 3) {
                cell.setCellValue(totalPakket);
            }
        }
    }

    @Override
    public String getFileOutputPath() {
        return fileOutputPath;
    }

    @Override
    public Workbook getWorkbook() {
        return workbook;
    }
    
}
