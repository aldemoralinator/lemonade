package com.aldemoralinator.lemonade.app.account;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aldemoralinator.lemonade.app.account.payload.response.AccountResponseDTO;
import com.aldemoralinator.lemonade.model.entity.Account;
import com.aldemoralinator.lemonade.model.entity.Project;
import com.aldemoralinator.lemonade.repository.AccountRepository;

@Service
public class AccountService {
	

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	public AccountResponseDTO getAccountById(Long id) throws Exception {
		Optional<Account> optnAccount = accountRepository.findById(id); 
		if (optnAccount.isEmpty()) throw new Exception("Account with id: " + id + " doesn't exist");
		Account account = optnAccount.get();
		return new AccountResponseDTO(account);
	}
	
	public Account queryAccountById(Long id) throws ResponseStatusException {
		Optional<Account> optnAccount = this.accountRepository.findById(id);
		if (optnAccount.isEmpty())
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Project with id: " + id + "does not exist");
		return optnAccount.get();
	}
	
	
}
