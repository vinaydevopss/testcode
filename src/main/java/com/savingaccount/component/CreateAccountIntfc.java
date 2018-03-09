package com.savingaccount.component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savingaccount.domain.Account;

@Repository
public interface CreateAccountIntfc extends JpaRepository<Account, Long> {

}
