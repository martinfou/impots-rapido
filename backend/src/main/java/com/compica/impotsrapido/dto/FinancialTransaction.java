package com.compica.impotsrapido.dto;

import java.util.Date;

public class FinancialTransaction {
	
	private Date date;
	private String category;
	private String description;
	private String detail;
	private int month;
	private double amount;
	
	public FinancialTransaction() {
		
	}
	
	public FinancialTransaction(Date date, String category, String description, String detail, int month,
			double amount) {
		this.date = date;
		this.category = category;
		this.description = description;
		this.detail = detail;
		this.month = month;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
	
}
