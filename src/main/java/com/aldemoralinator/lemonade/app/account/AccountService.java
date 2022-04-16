package com.aldemoralinator.lemonade.app.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldemoralinator.lemonade.app.account.payload.response.AccountResponseDTO;
import com.aldemoralinator.lemonade.model.Account;
import com.aldemoralinator.lemonade.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public AccountResponseDTO getAccountById(Long id) throws Exception {
		Optional<Account> optnAccount = accountRepository.findById(id); 
		if (optnAccount.isEmpty()) throw new Exception("account doesn't exist");
		Account account = optnAccount.get();
		return new AccountResponseDTO.Builder()
				.id(account.getId())
				.email(account.getEmail())
				.username(account.getUsername())
				.withRoles(account.getRoles())
				.build();
	}
}
