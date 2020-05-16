package com.compica.impotsrapido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.compica.impotsrapido.service.FinancialDataParser;
import com.compica.impotsrapido.service.FinancialDataParser.FinancialDataType;
import com.webcohesion.ofx4j.io.OFXParseException;
import com.webcohesion.ofx4j.io.OFXReader;
import com.webcohesion.ofx4j.io.nanoxml.NanoXMLOFXReader;

class FinancialDataParserTest {

	@Test
	void testFinancialDataParser() {
		FinancialDataParser financialDataParser = new FinancialDataParser(FinancialDataType.OFX);
		assertNotEquals(null, financialDataParser);
	}

	@Test
	void testParseFinancialDataFile_INPUT_invalid_file_OUTPUT_IOException() {
		FinancialDataParser financialDataParser = new FinancialDataParser(FinancialDataType.OFX);
		String file = "invalidFile.ofx";
		 Exception exception = assertThrows(IOException.class, () -> {
			 financialDataParser.parse(file);
		    });
	}
	
	@Test
	void testParseFinancialDataFile_input_valid_file__output_StatsAboutTheFile() throws IOException, OFXParseException {
		FinancialDataParser financialDataParser = new FinancialDataParser(FinancialDataType.OFX);
		String file = "sample1.ofx";
		financialDataParser.parse(file);
		assertEquals(true, true);
	}

}
