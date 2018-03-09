/**
 * 
 */
package com.savingaccount.util;

import org.springframework.stereotype.Component;

/**
 * @author Hackathon
 * This ElevenProofValidator class is used to validate the account number is eleven proof
 *
 */
@Component
public class ElevenProofValidator {

/**
 * Method to validate the Eleven Proof for an account Number
 * @param accountNo
 * @return
 */
	
	@SuppressWarnings("unused")
	public boolean validateAccountNumber(String accountNo) {
		char[] accArray = accountNo.toCharArray();
		int  temp=0;
		for(int i=0;i<accArray.length;i++) {
			temp = temp + ((accArray.length-i)*(Integer.parseInt(accArray[i]+"")));
		}
		if(temp%11==0) {
			return true;
		}else
		return false;
		
	}

}
