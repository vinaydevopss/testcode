package com.savingsaccount.service;

import com.savingaccount.exceptions.ValidationException;
import com.savingaccount.model.AccountTransactionRequest;

public interface AccountTransactionService {
	
	
	public String createTransaction(AccountTransactionRequest accountTransactionRequest) throws ValidationException;

}
