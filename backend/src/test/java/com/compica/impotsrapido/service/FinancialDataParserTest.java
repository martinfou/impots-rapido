package com.compica.impotsrapido.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.webcohesion.ofx4j.domain.data.common.TransactionList;
import com.webcohesion.ofx4j.io.OFXParseException;

class FinancialDataParserTest {

	@Test
	void testFinancialDataParser() {
		FinancialDataParser financialDataParser = new FinancialDataParser();
		assertNotEquals(null, financialDataParser);
	}

	@Test
	void testParseFinancialDataFile_INPUT_invalid_file_OUTPUT_IOException() {
		FinancialDataParser financialDataParser = new FinancialDataParser();
		String file = "invalidFile.ofx";
		assertThrows(IOException.class, () -> {
			 financialDataParser.parse(file);
		    });
	}
	
	@Test
	void testParseFinancialDataFile_input_valid_file_output_StatsAboutTheFile() throws IOException, OFXParseException {
		FinancialDataParser financialDataParser = new FinancialDataParser();
		String file = "stlaurent2019.ofx";
		TransactionList parse = financialDataParser.parse(file);
		assertEquals(164, parse.getTransactions().size());
	}
	
	@Test
	void testParseFinancialDataFile_input_valid_file_2019_output_StatsAboutTheFile() throws IOException, OFXParseException {
		FinancialDataParser financialDataParser = new FinancialDataParser();
		String file = "desecluses2019.ofx";
		TransactionList parse = financialDataParser.parse(file);
		
		assertEquals(204, parse.getTransactions().size());
	}

}
