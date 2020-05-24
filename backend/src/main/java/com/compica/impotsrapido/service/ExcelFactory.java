package com.compica.impotsrapido.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.compica.impotsrapido.dto.FinancialTransaction;

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
			List<FinancialTransaction> financialTrasactionList) {
		
		if(sheetName == null) {
			sheetName = "RAW_DATA";
		}
		
		if(columnNames == null) {
			columnNames = new LinkedList<>();
			columnNames.add("Column1");
		}
		
		if(financialTrasactionList == null) {
			financialTrasactionList = new LinkedList<FinancialTransaction>();
			FinancialTransaction ft= new FinancialTransaction();
			ft.setAmount(66.99);
			financialTrasactionList.add(ft);
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
        
        for (FinancialTransaction financialTransaction : financialTrasactionList) {
        	row = sheet.createRow(1);
			Cell cell = row.createCell(1);
			cell.setCellValue("biteme");
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