package com.aldemoralinator.lemonade.app.authentication.usernamepassword.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.aldemoralinator.lemonade.util.CommonStringUtil;

import lombok.Data;

@Data
public class UsernamePasswordRegisterRequestDTO {
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;
	
	public String getUsername() {
		return CommonStringUtil.extractUsername(email);
	}
}
