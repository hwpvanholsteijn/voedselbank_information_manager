package io;

import java.io.File;
import java.io.FileNotFoundException;

public class ExtensieBepaler {
    
    public static void checkBestaanFile(String filePath) throws FileNotFoundException {
        if (filePath != null && !filePath.equals("")) {
            File f = new File(filePath);
            if (!f.exists() && f.isDirectory()) {
                throw new FileNotFoundException("Bestand niet gevonden");
            }
        } else {
            throw new FileNotFoundException("Vul een bestandspath in.");
        }
    }
    
    public static String getFileExtensie(String filePath) {
        String extension = "";
        int i = filePath.lastIndexOf('.');
        int p = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));
        
        if (i > p) {
            extension = filePath.substring(i + 1);
        }
        return extension;
    }
}
