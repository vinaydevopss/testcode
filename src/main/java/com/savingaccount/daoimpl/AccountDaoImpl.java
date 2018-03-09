package com.savingaccount.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.savingaccount.dao.AccountDao;
import com.savingaccount.domain.Account;
import com.savingaccount.exceptions.ValidationException;
import com.savingaccount.util.TransactionValidationConstants;

@Repository
//@PropertySource("classpath:application.properties")
public class AccountDaoImpl implements AccountDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	public Account getAccount(String accountNumber) throws ValidationException
	{
		Account account;
	try
	{
		account=entityManager.createQuery(env.getProperty("getaccount"),Account.class).setParameter(1, accountNumber).setLockMode(LockModeType.PESSIMISTIC_WRITE).getSingleResult();
		
	}
	catch(NoResultException ex)
	{
		ex.printStackTrace();
		throw new ValidationException(TransactionValidationConstants.accountdoesnotExist);
	}
	return account;
	
}

}
