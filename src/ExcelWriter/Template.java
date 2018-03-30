/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelWriter;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Harrie
 */

public interface Template {
    
    public String getFileOutputPath();
    
    public Workbook getWorkbook();
    
    public void writeData();

}
