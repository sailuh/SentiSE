package edu.sentise.test.elimination;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.sentise.model.SentimentData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExcelWriter {

    private static String[] columns = {"Text", "Neutral", "Negative", "Positive"};
   // private static List<SentimentResults> sentiResults =  new ArrayList<>();

	public static void writeInExcel(ArrayList<SentimentResults> sentiResults )
	{
		 Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

	        /* CreationHelper helps us create instances for various things like DataFormat, 
	           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	        CreationHelper createHelper = workbook.getCreationHelper();

	        // Create a Sheet
	        Sheet sheet = workbook.createSheet("senti_res");

	        // Create a Font for styling header cells
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeight((short) 16);
	        headerFont.setColor(IndexedColors.RED.getIndex());

	        // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);

	        // Create a Row
	        Row headerRow = sheet.createRow(0);

	        // Creating cells
	        for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(headerCellStyle);
	        }

	        // Create Cell Style for formatting Date
	       // CellStyle dateCellStyle = workbook.createCellStyle();
	        //dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

	        // Create Other rows and cells with employees data
	        int rowNum = 1;
	        for(SentimentResults sr: sentiResults) {
	            Row row = sheet.createRow(rowNum++);

	            row.createCell(0)
	                    .setCellValue(sr.text);

	            row.createCell(1)
	                    .setCellValue(sr.neutral);

	           /* Cell dateOfBirthCell = row.createCell(2);
	            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
	            dateOfBirthCell.setCellStyle(dateCellStyle);*/
	            
	            row.createCell(2)
	            .setCellValue(sr.negative);
	            row.createCell(3)
	                    .setCellValue(sr.positive);
	        }

			// Resize all columns to fit the content size
	        for(int i = 0; i < columns.length; i++) {
	            sheet.autoSizeColumn(i);
	        }

	        // Write the output to a file
	        try
	        {
	        FileOutputStream fileOut = new FileOutputStream("sentise_res.xlsx");
	        workbook.write(fileOut);
	        fileOut.close();
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	}
	
    public static void main(String[] args) throws IOException, InvalidFormatException {
        // Create a Workbook
       
    }
}