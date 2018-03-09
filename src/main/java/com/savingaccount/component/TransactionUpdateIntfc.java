package com.savingaccount.component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savingaccount.domain.Transaction;

@Repository
public interface TransactionUpdateIntfc extends JpaRepository<Transaction,Long>  {

}

