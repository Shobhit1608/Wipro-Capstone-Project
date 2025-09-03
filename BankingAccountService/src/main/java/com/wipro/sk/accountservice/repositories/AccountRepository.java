package com.wipro.sk.accountservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sk.accountservice.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByCustomerId(Long customerId);
	
}
