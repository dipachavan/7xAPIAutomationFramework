package org.automation.com.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilsExcel {
    //It will be used by TestNG-DataProvider
    //It will read data from excel file and give u data in 2D matrix.
    public static String FileName="src/test/resources/TestData.xlsx";
    static Workbook workbook;
    static Sheet sheet;

    public static Object[][] getTestData(String sheetName)
    {
        FileInputStream fileInputStream=null;

        try {
            fileInputStream=new FileInputStream(FileName);
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        }

        try {
            workbook= WorkbookFactory.create(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sheet=workbook.getSheet(sheetName);
        Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) 
        {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++)
            {
                //here because of i+1, first row is skipped.
                data[i][j]=sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return data;

    }

    @DataProvider
    public Object[][] getData()
    {
        //THis function is for future use where I can write the logic to select which sheet I want to use.
        //ask user to input the sheet name
        //data.properties -> sheet1 or sheet2
        //e.g.
            // sheet1->QA
            // sheet2->Prod
        return getTestData("Sheet1");
    }
}
