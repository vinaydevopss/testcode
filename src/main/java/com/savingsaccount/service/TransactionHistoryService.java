package com.savingsaccount.service;

import java.text.ParseException;

import com.savingaccount.model.TransactionHistory;

public interface TransactionHistoryService {
	public TransactionHistory getTransactionHistory(String accoountNo,String fromdate,String toDate) throws ParseException;

}
