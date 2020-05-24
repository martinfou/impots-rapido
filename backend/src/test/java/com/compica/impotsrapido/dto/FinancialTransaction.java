package com.compica.impotsrapido.dto;

import java.util.Date;

import com.webcohesion.ofx4j.domain.data.common.TransactionType;

public class FinancialTransaction {
	
	private double amount;
	private TransactionType type;
	private String name;
	private Date transactionDate;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
