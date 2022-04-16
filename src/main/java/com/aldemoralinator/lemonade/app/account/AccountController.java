package com.aldemoralinator.lemonade.app.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aldemoralinator.lemonade.app.account.payload.response.AccountResponseDTO;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAccountById(@PathVariable Long id) throws Exception {
		AccountResponseDTO res = accountService.getAccountById(id);
		return ResponseEntity.ok(res);
	}
}
