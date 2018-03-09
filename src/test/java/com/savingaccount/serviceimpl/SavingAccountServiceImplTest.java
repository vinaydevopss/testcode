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

import com.savingaccount.component.CreateAccountIntfc;
import com.savingaccount.domain.Account;
import com.savingaccount.util.ElevenProofValidator;

@RunWith(MockitoJUnitRunner.class)


public class SavingAccountServiceImplTest {

	@InjectMocks
	private SavingAccountServiceImpl savingAccountServiceImpl;
	
	
	@Mock
	ElevenProofValidator validator;
	
	@Mock
	CreateAccountIntfc createAccountIntfc;
	
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		
	}

	
	
	@Test
	public void createSavingAccountTestWithCorrectInput() throws Exception{
		Account mockAccountt= new Account();
		mockAccountt.setAccountNumber("736160211");
		mockAccountt.setAmount(5000.00);
		mockAccountt.setCustomerNumber("ING0001234");
		Mockito.when(validator.validateAccountNumber(mockAccountt.getAccountNumber())).thenReturn(true);
		Mockito.when(createAccountIntfc.saveAndFlush(mockAccountt)).thenReturn(mockAccountt);
		Account result = savingAccountServiceImpl.createSavingAccount(mockAccountt);
		assertEquals(mockAccountt, result);

	}
	
	@Test
	public void createSavingAccountTestWithWrongInput() throws Exception{
		
		Account mockAccountt= new Account();
		mockAccountt.setAccountNumber("736160212");
		mockAccountt.setAmount(5000.00);
		mockAccountt.setCustomerNumber("ING0001234");
		Mockito.when(validator.validateAccountNumber(mockAccountt.getAccountNumber())).thenReturn(false);
		Mockito.when(createAccountIntfc.saveAndFlush(mockAccountt)).thenReturn(mockAccountt);
		//savingAccountServiceImpl.createSavingAccount(mockAccountt);
		Account result = savingAccountServiceImpl.createSavingAccount(mockAccountt);
		assertEquals(null, result);
		
	}
	
	@Test
	public void validationOfAccountwithChars() {
		Account mockAccountt= new Account();
		mockAccountt.setAccountNumber("xxxxxxxxx");
		mockAccountt.setAmount(5000.00);
		mockAccountt.setCustomerNumber("ING0001234");
		Mockito.when(validator.validateAccountNumber(mockAccountt.getAccountNumber())).thenReturn(false);
		Mockito.when(createAccountIntfc.saveAndFlush(mockAccountt)).thenReturn(mockAccountt);
		//savingAccountServiceImpl.createSavingAccount(mockAccountt);
		Account result = savingAccountServiceImpl.createSavingAccount(mockAccountt);
		assertEquals(null, result);
		
	}
	
	@Test
	public void validationOfAccountwithLessDigits() {
		Account mockAccountt= new Account();
		mockAccountt.setAccountNumber("736160");
		mockAccountt.setAmount(5000.00);
		mockAccountt.setCustomerNumber("ING0001234");
		Mockito.when(validator.validateAccountNumber(mockAccountt.getAccountNumber())).thenReturn(false);
		Mockito.when(createAccountIntfc.saveAndFlush(mockAccountt)).thenReturn(mockAccountt);
		//savingAccountServiceImpl.createSavingAccount(mockAccountt);
		Account result = savingAccountServiceImpl.createSavingAccount(mockAccountt);
		assertEquals(null, result);
	}
	
	@Test
	public void validationOfAccountwithEmptyAccountNumber() {
		Account mockAccountt= new Account();
		mockAccountt.setAccountNumber("");
		mockAccountt.setAmount(5000.00);
		mockAccountt.setCustomerNumber("ING0001234");
		Mockito.when(validator.validateAccountNumber(mockAccountt.getAccountNumber())).thenReturn(false);
		Mockito.when(createAccountIntfc.saveAndFlush(mockAccountt)).thenReturn(mockAccountt);
		//savingAccountServiceImpl.createSavingAccount(mockAccountt);
		Account result = savingAccountServiceImpl.createSavingAccount(mockAccountt);
		assertEquals(null, result);
		
	}

}
