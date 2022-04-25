package com.aldemoralinator.lemonade.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@ToString
@EqualsAndHashCode
public class AccountProjectKey implements Serializable {

	private static final long serialVersionUID = -190204697129503098L;

	@Getter
	@Setter
	@Column(name="user_id", nullable=false)
	private Long userId;

	@Getter
	@Setter
	@Column(name="project_id", nullable=false)
	private Long projectId;
	
	public AccountProjectKey() { }

	public AccountProjectKey(Long userId, Long projectId) {
		super();
		this.userId = userId;
		this.projectId = projectId;
	}
	
	
}
