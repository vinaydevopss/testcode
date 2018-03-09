package com.savingaccount.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savingaccount.component.CreateAccountIntfc;
import com.savingaccount.domain.Account;
import com.savingaccount.util.ElevenProofValidator;
import com.savingsaccount.service.SavingAccountService;

@Service
public class SavingAccountServiceImpl implements SavingAccountService{

	@Autowired
	private ElevenProofValidator validator;
	
	@Autowired
	private CreateAccountIntfc createAccountIntfc;

	@Override
	public Account createSavingAccount(Account account) {
	
		boolean check=validator.validateAccountNumber(account.getAccountNumber());
		Account accountReturned = null;
		if(check)
		{
			accountReturned = createAccountIntfc.saveAndFlush(account);
		}
		return accountReturned;
	} 
	
}
