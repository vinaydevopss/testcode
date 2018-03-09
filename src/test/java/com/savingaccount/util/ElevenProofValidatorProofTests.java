package com.savingaccount.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class ElevenProofValidatorProofTests {

	@Mock
	ElevenProofValidator validator;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void validationOfAccountwithCorrectInput() {
		String mockAccountt ="736160221";
		Mockito.when(validator.validateAccountNumber(mockAccountt)).thenReturn(true);
		assertTrue(validator.validateAccountNumber(mockAccountt));
		
	}
	@Test
	public void validationOfAccountwithWrongInput() {
		String mockAccountt ="736160222";
		Mockito.when(validator.validateAccountNumber(mockAccountt)).thenReturn(false);
		assertFalse(validator.validateAccountNumber(mockAccountt));
		
	}
	@Test
	public void validationOfAccountwithChars() {
		String mockAccountt ="xxxxxxxxx";
		Mockito.when(validator.validateAccountNumber(mockAccountt)).thenReturn(false);
		assertFalse(validator.validateAccountNumber(mockAccountt));
		
	}
	
	@Test
	public void validationOfAccountwithLessDigits() {
		String mockAccountt ="736160";
		Mockito.when(validator.validateAccountNumber(mockAccountt)).thenReturn(false);
		assertFalse(validator.validateAccountNumber(mockAccountt));
		
	}
	
	@Test
	public void validationOfAccountwithEmptyAccountNumber() {
		String mockAccountt ="";
		Mockito.when(validator.validateAccountNumber(mockAccountt)).thenReturn(false);
		assertFalse(validator.validateAccountNumber(mockAccountt));
		
	}
}
