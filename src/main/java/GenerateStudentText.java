import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenerateStudentText {

    public static ArrayList<String> FIELDSFORSTUDENTTXT;
    public static ArrayList<String> FIELDSFORSTUDENTDAT;

    public static void main(String[] args) throws IOException{

        FIELDSFORSTUDENTTXT = new ArrayList<String>();
        FIELDSFORSTUDENTDAT = new ArrayList<String>();

        FIELDSFORSTUDENTTXT.add("First Name");
        FIELDSFORSTUDENTTXT.add("Middle Name");
        FIELDSFORSTUDENTTXT.add("Last Name");
        FIELDSFORSTUDENTTXT.add("Student ID");
        FIELDSFORSTUDENTTXT.add("Parent/Guardian Phone");
        FIELDSFORSTUDENTTXT.add("Grade");
        FIELDSFORSTUDENTTXT.add("Homeroom");
        FIELDSFORSTUDENTTXT.add("School Location");


        String filePath = "A:\\KoskiFiles\\SampleData.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, Integer> columnMapping = new HashMap<String, Integer>();
        Row columnNames = sheet.getRow(0);
        Iterator<Cell> columnNamesCells = columnNames.cellIterator();

        while(columnNamesCells.hasNext()){
            Cell cell = columnNamesCells.next();
            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                columnMapping.put(cell.getStringCellValue(), cell.getColumnIndex());
            }
        }

        Iterator<Row> rows = sheet.iterator();
        rows.next();
        while(rows.hasNext()){
            System.out.println("*************************");
            Row row = rows.next();
            for(String s : FIELDSFORSTUDENTTXT){
                Cell cell = row.getCell(columnMapping.get(s));
                if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                    System.out.print(cell.getStringCellValue() + ",");
                }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    System.out.print(cell.getNumericCellValue() + ",");
                }
            }
            System.out.println();
        }
        workbook.close();
        inputStream.close();
    }

}
