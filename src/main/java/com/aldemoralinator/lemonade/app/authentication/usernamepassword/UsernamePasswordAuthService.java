package com.aldemoralinator.lemonade.app.authentication.usernamepassword;

import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aldemoralinator.lemonade.app.authentication.usernamepassword.payload.request.UsernamePasswordLoginRequestDTO;
import com.aldemoralinator.lemonade.app.authentication.usernamepassword.payload.request.UsernamePasswordRegisterRequestDTO;
import com.aldemoralinator.lemonade.app.authentication.usernamepassword.payload.response.UsernamePasswordAuthResponseDTO;
import com.aldemoralinator.lemonade.config.security.jwt.JWTUtil;
import com.aldemoralinator.lemonade.model.entity.Account;
import com.aldemoralinator.lemonade.model.entity.Role;
import com.aldemoralinator.lemonade.repository.AccountRepository;
import com.aldemoralinator.lemonade.repository.RoleRepository;

@Service
public class UsernamePasswordAuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthService.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UsernamePasswordAuthResponseDTO login(UsernamePasswordLoginRequestDTO reqDTO) {
		String username = reqDTO.getUsername();
		String password = reqDTO.getPassword();
		UsernamePasswordAuthenticationToken UPAToken = 
				new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(UPAToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateJwtToken(authentication);
		Account account = (Account) authentication.getPrincipal();
		return new UsernamePasswordAuthResponseDTO.Builder()
				.withId(account.getId())
				.withToken(jwt)
				.withUsername(account.getUsername())
				.withEmail(account.getEmail())
				.build();
	}
	
	public UsernamePasswordAuthResponseDTO register(UsernamePasswordRegisterRequestDTO reqDTO) {
		if (accountRepository.existsByUsername(reqDTO.getUsername()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists.");
		if (accountRepository.existsByEmail(reqDTO.getEmail()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists.");
		Role userRole = roleRepository.getById(1L); // DO NOT SUBMIT
		Account newAccount;
		try {
			newAccount = new Account.Builder(reqDTO.getEmail())
					.withPassword(passwordEncoder.encode(reqDTO.getPassword()))
					.withRoles()
							.addRole(userRole)
							.build()
					.build();
			accountRepository.save(newAccount);
		} catch (AddressException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		UsernamePasswordLoginRequestDTO newLoginReqDTO = new UsernamePasswordLoginRequestDTO(
				reqDTO.getUsername(), reqDTO.getPassword());
		return this.login(newLoginReqDTO);
	}
}
