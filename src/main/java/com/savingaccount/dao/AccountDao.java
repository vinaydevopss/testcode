package com.savingaccount.dao;

import com.savingaccount.domain.Account;
import com.savingaccount.exceptions.ValidationException;

public interface AccountDao {

	public Account getAccount(String accountNumber) throws ValidationException;
}
