package com.aldemoralinator.lemonade.app.account.payload.response;

import java.util.HashSet;
import java.util.Set;

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
	private Set<Role> roles;
	
	public AccountResponseDTO(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.email = builder.email;
		this.roles = builder.roles;
	}
	
	public static class Builder {
    	
    	private Long id;
    	private String username;
    	private String email;
    	private Set<Role> roles;
    	
        public Builder() {
        }
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public AccountResponseDTO build() {
            return new AccountResponseDTO(this);
        }
        

        public Builder withRoles(Set<Role> roles) {
        	if (roles.isEmpty()) 
        		throw new IllegalArgumentException("An Account must have atleast 1 role");
        	this.roles = roles;
        	return this;
        }
        
        public RolesBuilder withRoles() {
        	RolesBuilder rolesBuilder = new RolesBuilder(this);
        	return rolesBuilder;
        }
        
        public static class RolesBuilder {
	       	 
	       	 private Set<Role> roles = new HashSet<>();
	       	 private Builder builder;
	       	 
	       	 public RolesBuilder(Builder builder) { 
	       		 this.builder = builder;
	       	 }
	       	 
	       	 public RolesBuilder addRole(Role role) {
	       		 this.roles.add(role);
	       		 return this;
	       	 }
	       	 
	       	 public Builder build() {
	   		 	 return this.builder.withRoles(this.roles);
	       	 }
	   	}
    }
	
}
