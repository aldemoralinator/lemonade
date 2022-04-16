package com.aldemoralinator.lemonade.app.account.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AccountCreateRequestDTO {
	
	@NotEmpty
	@Email
	private String email;

}
