package com.aldemoralinator.lemonade.model.entity;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.aldemoralinator.lemonade.exception.ExceptionMessageBuilder;
import com.aldemoralinator.lemonade.util.CommonStringUtil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table( name="account" )
@ToString
@EqualsAndHashCode
public class Account implements UserDetails {

	private static final long serialVersionUID = -1954015940470597794L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter
	private Long id;
	
	@Getter
	private String username;
	
	@Getter
	private String email;
	
	@Getter
	private String password;
	
	@Getter
	private final boolean isAccountNonExpired = true;
	
	@Getter
	private final boolean isAccountNonLocked = true;
	
	@Getter
	private final boolean isCredentialsNonExpired = true;
	
	@Getter
	private final boolean isEnabled = true;
	
	@Getter 
	private Date created_at;
	
	@Getter 
	private Date updated_at;
	
	@OneToMany(mappedBy = "account")
    Set<AccountProject> accountProjects;
	
	@Getter
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="account_role",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public Account() { 
	}
	
	public Account(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.email = builder.email;
		this.password = builder.password;
		this.roles = builder.roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

    public static class Builder {
    	
    	private Long id;
    	private String username;
    	private String email;
    	private String password;
    	private Set<Role> roles;
    	
        public Builder(String email) throws AddressException {
        	this.email = email;
        	CommonStringUtil.throwErrIfValidEmailAddress(email);
            this.username = CommonStringUtil.extractUsername(email);
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
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

        public Account build() {
        	this.validate();
            return new Account(this);
        }
        
        private void validate() {
        	ExceptionMessageBuilder emb = new ExceptionMessageBuilder();
        	//if (this.id == null) emb.append("Id must not be null"); // DO NOT SUBMIT
        	if (this.username == null) emb.append("Username must not be null");
        	if (this.email == null) emb.append("Email must not be null");
        	emb.throwExceptionIfNotEmpty();
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