package com.aldemoralinator.lemonade.config.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aldemoralinator.lemonade.model.entity.Account;
import com.aldemoralinator.lemonade.repository.AccountRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> optnAccount = accountRepository.findByUsername(username);
		if (optnAccount.isEmpty()) {
			throw new UsernameNotFoundException("User with username" + username + " not found.");
		}			
		return optnAccount.get();
	}

}
