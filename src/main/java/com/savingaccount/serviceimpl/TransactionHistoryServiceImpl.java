package com.savingaccount.serviceimpl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savingaccount.dao.TransactionDao;
import com.savingaccount.domain.Transaction;
import com.savingaccount.model.TransactionHistory;
import com.savingsaccount.service.TransactionHistoryService;

@Service
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	TransactionDao transactionDao;
	@Override
	public TransactionHistory getTransactionHistory(String accoountNo, String fromdate, String toDate) throws ParseException {
		
		List<Transaction> transactionList=transactionDao.getTransactionDetails(accoountNo, fromdate, toDate);
		TransactionHistory transactionHistory=new TransactionHistory();
		transactionHistory.setTransactionList(transactionList);
		return transactionHistory;
	}

}
