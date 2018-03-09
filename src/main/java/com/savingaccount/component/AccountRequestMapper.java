package com.savingaccount.component;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.savingaccount.domain.Account;
import com.savingaccount.model.AccountCreationRequest;

@Component
public class AccountRequestMapper {

	public Account getAccount(AccountCreationRequest request)
	{
		Account account=new Account();
		account.setAccountNumber(request.getAccountNumber());
		account.setAmount(request.getAmount());
		account.setCreatedDatetime(new Date());
		account.setCurrency("EUR");
		account.setCustomerNumber(request.getCustomerNumber());
		account.setStatus("Active");
		account.setType("Savings Account");
		
		return account;
	}
}
