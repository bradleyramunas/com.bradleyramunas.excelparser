import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenerateStudentText {

    public static void createStudentTXT(String fileLocation, String storageLocation) throws IOException{
        PrintWriter printWriter = new PrintWriter(new File(storageLocation + "//testme.txt"));
        ArrayList<String> FIELDSFORSTUDENTTXT;
        FIELDSFORSTUDENTTXT = new ArrayList<String>();

        FIELDSFORSTUDENTTXT.add("First Name");
        FIELDSFORSTUDENTTXT.add("Middle Name");
        FIELDSFORSTUDENTTXT.add("Last Name");
        FIELDSFORSTUDENTTXT.add("Student ID");
        FIELDSFORSTUDENTTXT.add("Parent/Guardian Phone");
        FIELDSFORSTUDENTTXT.add("Grade");
        FIELDSFORSTUDENTTXT.add("Homeroom");
        FIELDSFORSTUDENTTXT.add("School Location");

        FileInputStream inputStream = new FileInputStream(new File(fileLocation));
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
            String built = "";
            Row row = rows.next();
            for(String s : FIELDSFORSTUDENTTXT){
                Cell cell = row.getCell(columnMapping.get(s));
                if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                    built += cell.getStringCellValue() + ",";
                }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    built += cell.getNumericCellValue() + ",";
                }
            }
            built = built.substring(0, built.length()-1);
            printWriter.println(built);

        }
        workbook.close();
        inputStream.close();
    }


}
