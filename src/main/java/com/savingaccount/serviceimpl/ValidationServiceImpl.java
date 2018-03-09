package com.savingaccount.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savingaccount.dao.AccountDao;
import com.savingaccount.domain.Account;
import com.savingaccount.exceptions.ValidationException;
import com.savingaccount.util.TransactionValidationConstants;
import com.savingsaccount.service.ValidationService;

@Service
@Transactional
public class ValidationServiceImpl implements ValidationService {
	
	@Autowired
	private AccountDao accountDao;
	
	String validationResult;
	
	public String exists(String accountNumber) {
		try
		{
		@SuppressWarnings("unused")
		Account account=accountDao.getAccount(accountNumber);
		validationResult=TransactionValidationConstants.accountExists;
		}
		catch (ValidationException ex)
		{
			validationResult=TransactionValidationConstants.accountdoesnotExist;
		}
		return validationResult;
	}
		
		
}
