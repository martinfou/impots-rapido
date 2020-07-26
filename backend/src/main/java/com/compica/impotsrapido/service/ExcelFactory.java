package com.compica.impotsrapido.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.webcohesion.ofx4j.domain.data.common.Transaction;
import com.webcohesion.ofx4j.domain.data.common.TransactionList;

public class ExcelFactory {
	
	public static void generateExcelDocument(Path path, String fileName) {
		generateExcelDocument(path, fileName, null,null,null);
	}
	
	public static void generateExcelDocument(Path path, String fileName, String sheetName) {
		generateExcelDocument(path, fileName, sheetName,null,null);
	}
	
	public static void generateExcelDocument(Path path, String fileName, String sheetName, List<String> columnNames) {
		generateExcelDocument(path, fileName, sheetName,columnNames,null);
	}

	public static void generateExcelDocument(
			Path path, 
			String fileName, 
			String sheetName, 
			List<String> columnNames,
			TransactionList financialTransactionList) {
		
		if(sheetName == null) {
			sheetName = "RAW_DATA";
		}
		
		if(columnNames == null) {
			columnNames = new LinkedList<>();
			columnNames.add("Column1");
		}
		 
		System.out.println("path " + path.toAbsolutePath().normalize());
		System.out.println("fileName " + fileName);
				
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
		
        int rowNum = 0;
        int columnNum = 0;
        System.out.println("Creating excel");
        Row row = sheet.createRow(rowNum);
        for (String columnName : columnNames) {
			Cell cell = row.createCell(columnNum++);
			cell.setCellValue(columnName);
			
		}
        
        for (Transaction financialTransaction : financialTransactionList.getTransactions()){
        	rowNum = rowNum + 1;
        	row=sheet.createRow(rowNum);
        	// Date	Categories	Description	Details	Mois	Montant
        	System.out.println(rowNum);
        	System.out.println(financialTransaction.toString());
			Cell cellDate = row.createCell(0);
        	Cell cellTransactionType = row.createCell(1);
        	Cell cellCategories = row.createCell(2);
        	Cell cellDescription = row.createCell(3);
        	Cell cellMemo = row.createCell(4);
        	Cell cellName = row.createCell(5);
        	Cell cellMois = row.createCell(6);
        	Cell cellMontant = row.createCell(7);

        	
        	Date datePosted = financialTransaction.getDatePosted();
        	Calendar calendar = Calendar.getInstance();

        	
        	calendar.setTime(datePosted);
        	int year = calendar.get(Calendar.YEAR);
        	int month = calendar.get(Calendar.MONTH)+1;
        	int day = calendar.get(Calendar.DAY_OF_MONTH);
        	cellDate.setCellValue(year+"-"+month+"-"+day);
        	cellTransactionType.setCellValue(financialTransaction.getTransactionType().toString());
        	cellCategories.setCellValue("cat");
        	cellDescription.setCellValue("desc");
        	cellMemo.setCellValue(financialTransaction.getMemo());
        	cellName.setCellValue(financialTransaction.getName());
        	cellMois.setCellValue(calendar.get(Calendar.MONTH)+1);
        	cellMontant.setCellValue(financialTransaction.getAmount());
        	
		}
        
        try {
    		String excelFile = path.toAbsolutePath().normalize().toString() + File.separator + fileName;
            FileOutputStream outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        System.out.println("Done");
	}
}