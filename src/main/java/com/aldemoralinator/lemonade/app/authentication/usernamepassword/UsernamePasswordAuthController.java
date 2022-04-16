package com.aldemoralinator.lemonade.app.authentication.usernamepassword;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aldemoralinator.lemonade.app.authentication.usernamepassword.payload.request.UsernamePasswordLoginRequestDTO;
import com.aldemoralinator.lemonade.app.authentication.usernamepassword.payload.request.UsernamePasswordRegisterRequestDTO;
import com.aldemoralinator.lemonade.app.authentication.usernamepassword.payload.response.UsernamePasswordAuthResponseDTO;

@RestController
@RequestMapping("/api/auth")
public class UsernamePasswordAuthController {
	
	@Autowired
	private UsernamePasswordAuthService usernamePasswordAuthService;
	
	@PostMapping("/login")
	public ResponseEntity<UsernamePasswordAuthResponseDTO> login(
			@Valid @RequestBody UsernamePasswordLoginRequestDTO reqDTO) {
		return ResponseEntity.ok(usernamePasswordAuthService.login(reqDTO));
	}
	
	@PostMapping("register")
	public ResponseEntity<UsernamePasswordAuthResponseDTO> register(
			@Valid @RequestBody UsernamePasswordRegisterRequestDTO reqDTO) {
		return ResponseEntity.ok(usernamePasswordAuthService.register(reqDTO));
	}
}
