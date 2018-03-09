package com.savingaccount.component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.savingaccount.domain.Account;

@Repository
public interface AccountUpdateIntfc extends JpaRepository<Account,Long> {
	
	@Modifying
	@Query("update Account a set a.amount=?1 where a.accountNumber = ?2")
	void updateAccount(double amount,String account);

}
