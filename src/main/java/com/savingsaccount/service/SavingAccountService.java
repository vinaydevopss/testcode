package com.savingsaccount.service;

import com.savingaccount.domain.Account;
@FunctionalInterface
public interface SavingAccountService {
	
	public Account createSavingAccount(Account account);

}
