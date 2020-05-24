package com.compica.impotsrapido.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFactory {
	
	public static void generateExcelDocument(Path path, String fileName) {
		generateExcelDocument(path, fileName, null,null);
	}
	
	public static void generateExcelDocument(Path path, String fileName, String sheetName) {
		generateExcelDocument(path, fileName, sheetName,null);
	}

	public static void generateExcelDocument(Path path, String fileName, String sheetName, List<String> columnNames) {
		
		if(sheetName == null) {
			sheetName = "RAW_DATA";
		}
		 
		System.out.println("path " + path.toAbsolutePath().normalize());
		System.out.println("fileName " + fileName);
		
		
		
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
		
        int rowNum = 0;
        System.out.println("Creating excel");

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