package com.aldemoralinator.lemonade.app.authentication.usernamepassword.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.aldemoralinator.lemonade.util.CommonStringUtil;

import lombok.Data;

@Data
public class UsernamePasswordLoginRequestDTO {
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	public UsernamePasswordLoginRequestDTO() {
	}
	
	public UsernamePasswordLoginRequestDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getUsername() {
		return CommonStringUtil.extractUsername(email);
	}
	
}
