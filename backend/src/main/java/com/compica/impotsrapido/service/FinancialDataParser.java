package com.compica.impotsrapido.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.webcohesion.ofx4j.domain.data.MessageSetType;
import com.webcohesion.ofx4j.domain.data.ResponseEnvelope;
import com.webcohesion.ofx4j.domain.data.banking.BankingResponseMessageSet;
import com.webcohesion.ofx4j.domain.data.common.TransactionList;
import com.webcohesion.ofx4j.io.AggregateUnmarshaller;
import com.webcohesion.ofx4j.io.OFXParseException;

public class FinancialDataParser {

	
	public enum FinancialDataType {
		OFX
	}

	public TransactionList parse(String financialDataFile) throws IOException, OFXParseException {
			
		URL fileUrl = this.getClass().getClassLoader().getResource(financialDataFile);
		if(null==fileUrl) {
			throw new IOException("file " + financialDataFile + " not found.");
		}
		InputStream file = new FileInputStream(fileUrl.getFile());
		 AggregateUnmarshaller<ResponseEnvelope> unmarshaller  = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);
		 ResponseEnvelope envelope = unmarshaller.unmarshal(file);
		 file.close();
		 BankingResponseMessageSet messageSet = (BankingResponseMessageSet) envelope.getMessageSet(MessageSetType.banking);
		 
		 return messageSet.getStatementResponses().get(0).getMessage().getTransactionList();	
	}

}
