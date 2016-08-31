import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Test {

    public static void main(String[] args) throws IOException{
        String filePath = "C:\\Users\\Bradley\\Desktop\\Book1.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        while(rows.hasNext()){
            Row row = rows.next();
            Iterator<Cell> cells = row.cellIterator();
            while(cells.hasNext()){
                Cell cell = cells.next();
                if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                    System.out.println(cell.getStringCellValue());
                }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
                    System.out.println(cell.getBooleanCellValue());
                }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    System.out.println(cell.getNumericCellValue());
                }
            }
        }
        workbook.close();
        inputStream.close();
    }

}
