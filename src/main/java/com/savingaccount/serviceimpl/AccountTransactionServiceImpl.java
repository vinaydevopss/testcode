package com.savingaccount.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savingaccount.component.AccountUpdateIntfc;
import com.savingaccount.component.TransactionHistoryMapper;
import com.savingaccount.component.TransactionUpdateIntfc;
import com.savingaccount.dao.AccountDao;
import com.savingaccount.domain.Account;
import com.savingaccount.exceptions.ValidationException;
import com.savingaccount.model.AccountTransactionRequest;
import com.savingaccount.util.TransactionValidationConstants;
import com.savingsaccount.service.AccountTransactionService;

@Service
@Transactional
public class AccountTransactionServiceImpl implements AccountTransactionService{

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private AccountUpdateIntfc accountUpdateIntfc;
	
	@Autowired
	private TransactionUpdateIntfc transactionUpdateIntfc;
	
	@Autowired
	private TransactionHistoryMapper transactionHistoryMapper;
	
	@Override
	public String createTransaction(AccountTransactionRequest accountTransactionRequest) throws ValidationException {
		
		Account account=accountDao.getAccount(accountTransactionRequest.getAccountNumber());
		if(accountTransactionRequest.getType().equalsIgnoreCase(TransactionValidationConstants.transactionTypeDebit))
		{
			if(accountTransactionRequest.getAmount()>account.getAmount())
			{
				return TransactionValidationConstants.doesNotqualifyForDebitTransaction;
			}
			
			
			double tempAccBalance=(account.getAmount())-(accountTransactionRequest.getAmount());
			accountUpdateIntfc.updateAccount(tempAccBalance, account.getAccountNumber());
			
			transactionUpdateIntfc.saveAndFlush(transactionHistoryMapper.mapTransaction(accountTransactionRequest));
			
		}
		
		if(accountTransactionRequest.getType().equalsIgnoreCase(TransactionValidationConstants.transactionTypeCredit))
		{
			if(accountTransactionRequest.getAmount()<0)
			{
				return TransactionValidationConstants.doesNotqualifyForCreditTransaction;
			}
			
			
			double tempAccBalance=(account.getAmount())+(accountTransactionRequest.getAmount());
			accountUpdateIntfc.updateAccount(tempAccBalance, account.getAccountNumber());
			transactionUpdateIntfc.saveAndFlush(transactionHistoryMapper.mapTransaction(accountTransactionRequest));
			
		}
		
		return TransactionValidationConstants.transactionsuccess;
	}

    
}
