package com.savingaccount.component;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.savingaccount.domain.Transaction;
import com.savingaccount.model.AccountTransactionRequest;

@Component
public class TransactionHistoryMapper {
	
	public Transaction mapTransaction(AccountTransactionRequest accountTransactionRequest)
	{
		Transaction transaction=new Transaction();
		transaction.setAccountNumber(accountTransactionRequest.getAccountNumber());
		transaction.setAmount(accountTransactionRequest.getAmount());
		transaction.setCreatedDatetime(new Date());
		transaction.setCustomerNumber(accountTransactionRequest.getCustomerNumber());
		transaction.setType(accountTransactionRequest.getType());
		return transaction;
	}

}
