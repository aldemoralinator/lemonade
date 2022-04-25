package com.aldemoralinator.lemonade.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.aldemoralinator.lemonade.exception.ExceptionMessageBuilder;
import com.aldemoralinator.lemonade.model.key.AccountProjectKey;

import lombok.Getter;

@Entity
public class AccountProject {

	@EmbeddedId
	@Getter
	private AccountProjectKey id;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name="user_id")
	@Getter
	private Account account;
	
	@ManyToOne
	@MapsId("projectId")
	@JoinColumn(name="project_id")
	@Getter
	private Project project;
	
	@Getter
	@Column(name="is_subscribed", nullable=false)
	private boolean isSubscribed;
	
	@Getter
	@Column(name="created_at", nullable=false)
	private Date createdAt;
	
	@Getter
	@Column(name="updated_at", nullable=false)
	private Date updatedAt;
	
	public AccountProject(Builder builder) {
		this.id = builder.id;
		this.account = builder.account;
		this.project = builder.project;
		this.isSubscribed = false;
	}
	
	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	
	public static class Builder {
		
		private AccountProjectKey id;
		private Account account;
		private Project project;
		
		public Builder account(Account account) {
			this.account = account;
			return this;
		}
		
		public Builder project(Project project) {
			this.project = project;
			return this;
		}
		
		public AccountProject build() {
			this.id = new AccountProjectKey(this.account.getId(), this.project.getId());
			this.validate();
			return new AccountProject(this);
		}
		
		public void validate() {
			ExceptionMessageBuilder emb = new ExceptionMessageBuilder();
			if (this.id == null) emb.append("Composite Id must not be null");
        	if (this.account == null) emb.append("Account must not be null");
        	if (this.project == null) emb.append("Project must not be null");
        	if (this.account.getId() == null) emb.append("Account Id must not be null");
        	if (this.project.getId() == null) emb.append("Project Id must not be null");
        	emb.throwExceptionIfNotEmpty();
		}
	}
}
