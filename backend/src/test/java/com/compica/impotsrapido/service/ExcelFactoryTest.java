package com.compica.impotsrapido.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import com.compica.impotsrapido.dto.FinancialTransaction;
import com.webcohesion.ofx4j.domain.data.common.TransactionList;
import com.webcohesion.ofx4j.io.OFXParseException;

public class ExcelFactoryTest {
	
	final Path outputDir = Paths.get(".", "src","test","resources","output");

	@Test
	void generateExcelDocument_() throws IOException {
		String fileName = "testExcelCreation.xlsx";
		ExcelFactory.generateExcelDocument(outputDir, fileName);
		File tmpFile = new File(outputDir.toAbsolutePath().normalize() + File.separator + fileName);
		assertEquals(true, tmpFile.exists());
		assertEquals(true, tmpFile.delete());
	}

	@Test
	void generateExcelDocument_INPUT_sheet_name_testTabName_OUTPUT_excel_document_with_sheet_name_testTabName()
			throws IOException {
		String fileName = "testExcelTab.xlsx";
		String tabName = "testTabName";
		ExcelFactory.generateExcelDocument(outputDir, fileName, tabName);
		File tmpFile = new File(outputDir.toAbsolutePath().normalize() + File.separator + fileName);
		FileInputStream excelFile = new FileInputStream(tmpFile);
		
		
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet excelTab = workbook.getSheet(tabName);
		workbook.close();

		assertEquals(true, tmpFile.exists());
		assertNotEquals(null, excelTab);
		assertEquals(true,tmpFile.delete());
	}
	
	@Test
	void generateExcelDocument_INPUT_valid_column_name_OUTPUT_excel_document_with_valid_column_name()
			throws IOException {
		String fileName = "testExcelColumnName.xlsx";
		String tabName = "testTabName";
		List<String> columnNames = new LinkedList<>();
		columnNames.add("Date");
		columnNames.add("Categories");
		columnNames.add("Description");
		columnNames.add("transaction Details");
		columnNames.add("Mois");
		columnNames.add("Montant");
		
		ExcelFactory.generateExcelDocument(outputDir, fileName, tabName,columnNames);
		File tmpFile = new File(outputDir.toAbsolutePath().normalize() + File.separator + fileName);
		FileInputStream excelFile = new FileInputStream(tmpFile);
		
		
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet excelTab = workbook.getSheet(tabName);
		workbook.close();

		assertEquals(true, tmpFile.exists());
		assertNotEquals(null, excelTab);
		//assertEquals(true,tmpFile.delete());
	}
	
	@Test
	void generateExcelDocument_INPUT_valid_rows_OUTPUT_excel_document_with_204_rows()
			throws IOException, OFXParseException {
		String fileName = "testExcelRowData.xlsx";
		String tabName = "testTabName";
		List<String> columnNames = new LinkedList<>();
		columnNames.add("Date");
		columnNames.add("Categories");
		columnNames.add("Description");
		columnNames.add("Details");
		columnNames.add("Mois");
		columnNames.add("Montant");
		
		List<FinancialTransaction> financialTrasactionList = new LinkedList<FinancialTransaction>();
		FinancialTransaction financialTransaction = new FinancialTransaction();
		financialTransaction.setDate(new Date());
		financialTransaction.setCategory("9270 - Frais bancaire");
		financialTransaction.setDescription("test description");
		financialTransaction.setDetail("test details");
		financialTransaction.setMonth(12);
		financialTransaction.setAmount(66.99);

		ExcelFactory.generateExcelDocument(outputDir, fileName, tabName,columnNames,financialTrasactionList);
		File tmpFile = new File(outputDir.toAbsolutePath().normalize() + File.separator + fileName);
		FileInputStream excelFile = new FileInputStream(tmpFile);
		
		
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet excelTab = workbook.getSheet(tabName);
		workbook.close();

		assertEquals(true, tmpFile.exists());
		assertNotEquals(null, excelTab);
		//assertEquals(true,tmpFile.delete());
	}

}
