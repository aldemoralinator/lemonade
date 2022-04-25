package com.aldemoralinator.lemonade.app.project.payload.response;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.aldemoralinator.lemonade.app.account.payload.response.AccountResponseDTO;
import com.aldemoralinator.lemonade.exception.ExceptionMessageBuilder;
import com.aldemoralinator.lemonade.model.entity.Account;
import com.aldemoralinator.lemonade.model.entity.Project;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ProjectResponseDTO {

	@Getter
	private Long id;
	
	@Getter
	private String name;
	
	@Getter
	private String githubLink;
	
	@Getter
	private AccountResponseDTO createdBy;
	
	@Getter
	private Date createdAt;
	
	@Getter
	private Date updatedAt;
	
	@Getter
	private Set<AccountResponseDTO> accounts = new HashSet<>();
	
	public ProjectResponseDTO(Project project) {
		this.id = project.getId();
		this.name = project.getName();
		this.githubLink = project.getGithubLink();
		this.createdBy = new AccountResponseDTO(project.getCreatedBy());
		this.createdAt = project.getCreatedAt();
		this.updatedAt = project.getUpdatedAt();
		this.accounts = project.getAccountProjects().stream().map(
				accountProject -> new AccountResponseDTO(accountProject.getAccount())
				).collect(Collectors.toSet());
	}
}
