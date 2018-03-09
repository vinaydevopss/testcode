package com.savingaccount.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savingaccount.component.AccountRequestMapper;
import com.savingaccount.component.CreateAccountIntfc;
import com.savingaccount.exceptions.ValidationException;
import com.savingaccount.model.AccountCreationRequest;
import com.savingaccount.model.AccountTransactionRequest;
import com.savingaccount.model.SuccessResult;
import com.savingaccount.model.TransactionHistory;
import com.savingaccount.util.ElevenProofValidator;
import com.savingaccount.util.TransactionValidationConstants;
import com.savingsaccount.service.AccountTransactionService;
import com.savingsaccount.service.TransactionHistoryService;
import com.savingsaccount.service.ValidationService;

@RestController
public class SavingAccountController {
	
	@Autowired
	private ElevenProofValidator validator;
	
	@Autowired
	private CreateAccountIntfc createAccountIntfc;
	
	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private AccountRequestMapper mapper;
	
	@Autowired
	private AccountTransactionService accountTransactionService;
	
	@Autowired
	private TransactionHistoryService transactionHistoryService;
	
	@PostMapping("/accounts/register")
	public ResponseEntity<SuccessResult> createAccount(@RequestBody AccountCreationRequest request) throws ValidationException
	{
		SuccessResult result=new SuccessResult();
		boolean check=validator.validateAccountNumber(request.getAccountNumber());
		if(check==true)
		{
			createAccountIntfc.saveAndFlush(mapper.getAccount(request));
		}
		else
		{
			throw new ValidationException("Invalid Account Number");
		}
		
		result.setMessage("Account Created successfully");
		return new ResponseEntity<SuccessResult>(result,HttpStatus.OK);
	}
	
	@PostMapping("/accounts/transaction")
	public ResponseEntity<SuccessResult> createAccountTransaction(@RequestBody AccountTransactionRequest transactionRequest) throws ValidationException
	{
		String validate=validationService.exists(transactionRequest.getAccountNumber());
		
		if(validate.equalsIgnoreCase(TransactionValidationConstants.accountdoesnotExist))
		{
			throw new ValidationException("Account does not exist");
		}
		
		String transactionResult=accountTransactionService.createTransaction(transactionRequest);
		 if (transactionResult.equalsIgnoreCase(TransactionValidationConstants.doesNotqualifyForDebitTransaction))
		 {
			 throw new ValidationException(TransactionValidationConstants.doesNotqualifyForDebitTransaction);
		 }
		 
		 if (transactionResult.equalsIgnoreCase(TransactionValidationConstants.doesNotqualifyForCreditTransaction))
		 {
			 throw new ValidationException(TransactionValidationConstants.doesNotqualifyForCreditTransaction);
		 }
		
		
		
		
		SuccessResult result=new SuccessResult();
		//result.setMessage(TransactionValidationConstants.transactionsuccess);
		result.setMessage(transactionRequest.getType()+" on account "+transactionRequest.getAccountNumber()+" for amount of "+transactionRequest.getAmount()+" was successful");
		return new ResponseEntity<SuccessResult>(result,HttpStatus.OK);
	}
	
	@GetMapping("/accounts/getTransactionHistory")
    public ResponseEntity<TransactionHistory> getHistory(@RequestParam("accountNo") String accountNo,@RequestParam("toDate") String toDate,@RequestParam("fromDate") String fromDate) throws ParseException {
		TransactionHistory transactionHistory=transactionHistoryService.getTransactionHistory(accountNo, fromDate,toDate);
		return new ResponseEntity<>(transactionHistory,HttpStatus.OK);
        
		
    }
	
	

}
