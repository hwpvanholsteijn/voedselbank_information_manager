/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Harrie
 */

public class ExcelWriter {
    
    private final Template template;
    private final Workbook workbook;
    
    public ExcelWriter(Template template) {
        this.template = template;
        template.writeData();
        this.workbook = template.getWorkbook();
    }
    
    public void MakeExcel() {
        try {
            FileOutputStream out = new FileOutputStream(template.getFileOutputPath());
            workbook.write(out);
            out.close();
            System.out.println("Writing succesfull");
        } catch (IOException ex) {
            System.out.println("!!File Error!!");
        }
    }

}
