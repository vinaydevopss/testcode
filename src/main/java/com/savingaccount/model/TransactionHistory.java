package com.savingaccount.model;

import java.util.List;

import com.savingaccount.domain.Transaction;

public class TransactionHistory {
	
	private List<Transaction> transactionList;

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	

}
