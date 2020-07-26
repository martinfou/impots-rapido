package com.compica.impotsrapido.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.webcohesion.ofx4j.domain.data.MessageSetType;
import com.webcohesion.ofx4j.domain.data.ResponseEnvelope;
import com.webcohesion.ofx4j.domain.data.banking.BankingResponseMessageSet;
import com.webcohesion.ofx4j.domain.data.common.TransactionList;
import com.webcohesion.ofx4j.io.AggregateUnmarshaller;
import com.webcohesion.ofx4j.io.OFXParseException;

@Component
public class FinancialDataParser {

	
	public enum FinancialDataType {
		OFX
	}

	public TransactionList parse(File financialDataFile) throws IOException, OFXParseException {
			
		InputStream file = new FileInputStream(financialDataFile);
		 AggregateUnmarshaller<ResponseEnvelope> unmarshaller  = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);
		 ResponseEnvelope envelope = unmarshaller.unmarshal(file);
		 file.close();
		 BankingResponseMessageSet messageSet = (BankingResponseMessageSet) envelope.getMessageSet(MessageSetType.banking);
		 
		 return messageSet.getStatementResponses().get(0).getMessage().getTransactionList();	
	}

}
