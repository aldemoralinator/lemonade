package com.aldemoralinator.lemonade.app.account.payload.response;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.aldemoralinator.lemonade.model.entity.Account;
import com.aldemoralinator.lemonade.model.entity.Role;

import lombok.Getter;

public class AccountResponseDTO {
	
	@Getter
	private Long id;
	
	@Getter
	private String username;
	
	@Getter
	private String email;
	
	@Getter
	private Set<RoleResponseDTO> roles;
	
	public AccountResponseDTO() { }
	
	public AccountResponseDTO(Account account) {
		this.id = account.getId();
		this.username = account.getUsername();
		this.email = account.getEmail();
		this.roles = account.getRoles().stream().map(
				role -> new RoleResponseDTO(role)).collect(Collectors.toSet());
	}
}
