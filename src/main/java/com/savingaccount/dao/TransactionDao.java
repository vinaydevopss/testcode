package com.savingaccount.dao;

import java.text.ParseException;
import java.util.List;

import com.savingaccount.domain.Transaction;

public interface TransactionDao {
	public List<Transaction> getTransactionDetails(String accoountNo, String fromdate, String toDate) throws ParseException;

}
