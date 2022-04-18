package com.aldemoralinator.lemonade.model.entity;

import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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
	private boolean isSubscribed;
	
	@Getter
	private Date subscribed_at;
	
	@Getter
	private Date created_at;
	
	@Getter
	private Date updated_at;
}
