package com.aldemoralinator.lemonade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aldemoralinator.lemonade.model.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByUsername(String username);
	 
	Boolean existsByUsername(String username);
	    
	Boolean existsByEmail(String email);
}
