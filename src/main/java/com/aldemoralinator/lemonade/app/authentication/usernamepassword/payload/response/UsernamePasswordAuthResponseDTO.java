package com.aldemoralinator.lemonade.app.authentication.usernamepassword.payload.response;

import com.aldemoralinator.lemonade.exception.ExceptionMessageBuilder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UsernamePasswordAuthResponseDTO {

	@Getter
	private Long id;
	
	@Getter
	private String token;
	
	@Getter
	private final String type = "Bearer ";
	
	@Getter
	private String username;
	
	@Getter
	private String email;
	
	public UsernamePasswordAuthResponseDTO() { }
	
	public UsernamePasswordAuthResponseDTO(Builder builder) {
		this.id = builder.id;
		this.token = builder.token;
		this.username = builder.username;
		this.email = builder.email;
	}
	
	public static class Builder {
    	
    	private Long id;
    	private String token;
    	private String username;
    	private String email;
    	
        public Builder() {
        }
        
        public Builder withId(Long id) {
        	this.id = id;
        	return this;
        }
        
        public Builder withToken(String token) {
        	this.token = token;
        	return this;
        }
        
        public Builder withUsername(String username) {
        	this.username = username;
        	return this;
        }
        
        public Builder withEmail(String email) {
        	this.email = email;
        	return this;
        }

        public UsernamePasswordAuthResponseDTO build() {
        	this.validate();
            return new UsernamePasswordAuthResponseDTO(this);
        }
        
        private void validate() {
        	ExceptionMessageBuilder emb = new ExceptionMessageBuilder();
        	if (this.id == null) emb.append("Id must not be null");
        	if (this.token == null) emb.append("Token must not be null");
        	if (this.username == null) emb.append("Username must not be null");
        	if (this.email == null) emb.append("Email must not be null");
        	emb.throwExceptionIfNotEmpty();
        }
        
    }
}
