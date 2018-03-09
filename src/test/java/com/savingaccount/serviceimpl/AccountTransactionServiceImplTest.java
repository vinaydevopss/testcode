package com.savingaccount.serviceimpl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.savingaccount.component.AccountUpdateIntfc;
import com.savingaccount.component.TransactionHistoryMapper;
import com.savingaccount.component.TransactionUpdateIntfc;
import com.savingaccount.dao.AccountDao;
import com.savingaccount.domain.Account;
import com.savingaccount.exceptions.ValidationException;
import com.savingaccount.model.AccountTransactionRequest;
import com.savingaccount.util.TransactionValidationConstants;

@RunWith(MockitoJUnitRunner.class)
public class AccountTransactionServiceImplTest {

	@InjectMocks
	AccountTransactionServiceImpl accountTranService;
	
	@Mock
	AccountDao accountDao;
	
	@Mock
	private AccountUpdateIntfc accountUpdateIntfc;
	
	@Mock
	private TransactionUpdateIntfc transactionUpdateIntfc;
	
	@Mock
	private TransactionHistoryMapper transactionHistoryMapper;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	//@Test
	public void createTransactionTestwithCorrectAccountNumber() throws ValidationException {
		String accountNo="736160211";
		Account account = new Account();
		AccountTransactionRequest request = new AccountTransactionRequest();
		request.setAccountNumber(accountNo);
		request.setAmount(5000.00);
		request.setCustomerNumber("ING0001234");
		request.setType("Credit");
		Mockito.when(accountDao.getAccount(accountNo)).thenReturn(account);
		accountTranService.createTransaction(request);
		
	}
	@Test
	public void createTransactionTestwithDebitTransaction() throws ValidationException {
		String accountNo="736160211";
		Account account = new Account();
		AccountTransactionRequest request = new AccountTransactionRequest();
		request.setAccountNumber(accountNo);
		request.setAmount(5000.00);
		request.setCustomerNumber("ING0001234");
		request.setType("Debit");
		Mockito.when(accountDao.getAccount(accountNo)).thenReturn(account);
		String result = accountTranService.createTransaction(request);
		assertEquals(TransactionValidationConstants.doesNotqualifyForDebitTransaction,result);
	}
	
	@Test
	public void createTransactionTestwithCreditTransaction() throws ValidationException {
		String accountNo="736160211";
		Account account = new Account();
		AccountTransactionRequest request = new AccountTransactionRequest();
		request.setAccountNumber(accountNo);
		request.setAmount(5000.00);
		request.setCustomerNumber("ING0001234");
		request.setType("Credit");
		Mockito.when(accountDao.getAccount(accountNo)).thenReturn(account);
		String result = accountTranService.createTransaction(request);
		assertEquals("success",result);
	}
	
}
