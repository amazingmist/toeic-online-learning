package vn.myclass.core.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelPoiUtil {
//    using factory method pattern to build workbook
    public static Workbook getWorkBook(String fileLocation) throws IOException {
        File excelFile = new File(fileLocation);
        String fileName = excelFile.getName();
        InputStream inputStream = new FileInputStream(excelFile);

        Workbook workbook = null;
        if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }
}
