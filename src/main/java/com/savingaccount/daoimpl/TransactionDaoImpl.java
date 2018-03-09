package com.savingaccount.daoimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.savingaccount.dao.TransactionDao;
import com.savingaccount.domain.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Environment env;

	@Override
	public List<Transaction> getTransactionDetails(String accoountNo, String fromdate, String toDate) throws ParseException {
		Date histToDate=parseDate(toDate,"yyyy-MM-dd");
		Date histFromDate=parseDate(fromdate,"yyyy-MM-dd");
		List<Transaction> transactions= entityManager.createQuery(env.getProperty("getTransactionHistory"),Transaction.class).setParameter(1, accoountNo).setParameter(fromdate,TemporalType.DATE).setParameter("enddate", histToDate,TemporalType.DATE).setLockMode(LockModeType.PESSIMISTIC_WRITE).getResultList();
		return transactions;
	}
	
	private Date parseDate(String date, String format) throws ParseException 
	{
	    SimpleDateFormat formatter = new SimpleDateFormat(format);
	    return formatter.parse(date);
	}

}
