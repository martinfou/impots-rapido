package com.compica.impotsrapido.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.webcohesion.ofx4j.domain.data.MessageSetType;
import com.webcohesion.ofx4j.domain.data.ResponseEnvelope;
import com.webcohesion.ofx4j.domain.data.banking.AccountType;
import com.webcohesion.ofx4j.domain.data.banking.BankStatementResponse;
import com.webcohesion.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import com.webcohesion.ofx4j.domain.data.banking.BankingResponseMessageSet;
import com.webcohesion.ofx4j.domain.data.common.Transaction;
import com.webcohesion.ofx4j.domain.data.common.TransactionList;
import com.webcohesion.ofx4j.io.AggregateUnmarshaller;
import com.webcohesion.ofx4j.io.OFXHandler;
import com.webcohesion.ofx4j.io.OFXParseException;
import com.webcohesion.ofx4j.io.OFXReader;
import com.webcohesion.ofx4j.io.nanoxml.NanoXMLOFXReader;

public class FinancialDataParser {

	private FinancialDataType financialDataType;

	public FinancialDataParser(FinancialDataType financialDataType) {
		this.financialDataType = financialDataType;
	}
	
	

	public enum FinancialDataType {
		OFX
	}

	public ResponseEnvelope parse(String financialDataFile) throws IOException, OFXParseException {
		URL fileUrl = this.getClass().getClassLoader().getResource(financialDataFile);
		if(null==fileUrl) {
			throw new IOException("file " + financialDataFile + " not found.");
		}
		InputStream file = new FileInputStream(fileUrl.getFile());
		 AggregateUnmarshaller<ResponseEnvelope> unmarshaller  = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);
		 ResponseEnvelope envelope = unmarshaller.unmarshal(file);
		 BankingResponseMessageSet messageSet = (BankingResponseMessageSet) envelope.getMessageSet(MessageSetType.banking);
		 List<BankStatementResponseTransaction> responses = messageSet.getStatementResponses();
         for (BankStatementResponseTransaction response : responses) {

             BankStatementResponse message = response.getMessage();
             String currencyCode = message.getCurrencyCode();
             String acct_number = message.getAccount().getAccountNumber();
             double av = message.getAvailableBalance().getAmount();
             double cur = message.getLedgerBalance().getAmount();
             AccountType acct_type = message.getAccount().getAccountType();
             
             TransactionList transactionList = message.getTransactionList();
             
             for(Transaction transaction : message.getTransactionList().getTransactions()) {
            	 transaction.getAmount();
            	 transaction.getName();
            	 Date mydate = transaction.getDatePosted();
            	 int year = mydate.getYear();
            	 int month = mydate.getMonth();
            	 int day = mydate.getDay();
            	 System.out.println(transaction.getDatePosted() + " " + transaction.getName() + " " + transaction.getAmount() + " " +  transaction.toString());	 
             }
             
             System.out.println(currencyCode + " " + acct_number + " " + av + " " + acct_type);
         }
		return envelope;
		
	}

}
