package io;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class LocalDateMaker {

    public static LocalDate localDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate localDateFromString(double d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse("02-03-0003", formatter);
    }

    public static LocalDate getDateFromCell(Cell cell) {
        LocalDate parsedDate = null;
        if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
            if (DateUtil.isCellDateFormatted(cell)) {
                Date cellDate = DateUtil.getJavaDate(cell.getNumericCellValue());
                parsedDate = cellDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
        }
        return parsedDate;
    }

}
