package com.aldemoralinator.lemonade.app.project;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.aldemoralinator.lemonade.app.account.AccountService;
import com.aldemoralinator.lemonade.app.project.payload.request.ProjectCreateRequestDTO;
import com.aldemoralinator.lemonade.app.project.payload.request.ProjectUpdateRequestDTO;
import com.aldemoralinator.lemonade.app.project.payload.response.ProjectListResponseDTO;
import com.aldemoralinator.lemonade.app.project.payload.response.ProjectResponseDTO;
import com.aldemoralinator.lemonade.model.entity.Account;
import com.aldemoralinator.lemonade.model.entity.AccountProject;
import com.aldemoralinator.lemonade.model.entity.Project;
import com.aldemoralinator.lemonade.repository.AccountProjectRepository;
import com.aldemoralinator.lemonade.repository.AccountRepository;
import com.aldemoralinator.lemonade.repository.ProjectRepository;

@Service
public class ProjectService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountProjectRepository accountProjectRepository;
	
	public ProjectResponseDTO createProject(ProjectCreateRequestDTO createDTO) {
		Account authAccount = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Project newProject = new Project.Builder()
				.createdBy(authAccount)
				.name(createDTO.getName())
				.githubLink(createDTO.getGithubLink())
				.build();
		projectRepository.save(newProject);
		AccountProject newAccountProject = new AccountProject.Builder()
				.account(authAccount)
				.project(newProject)
				.build();
		accountProjectRepository.save(newAccountProject);
		return new ProjectResponseDTO(newProject);
	}
	
	public ProjectResponseDTO getProjectById(Long id) throws ResponseStatusException {
		Project project = this.queryProjectById(id);
		return new ProjectResponseDTO(project);
	}
	
	public ProjectResponseDTO updateProjectById(ProjectUpdateRequestDTO updateDTO) throws ResponseStatusException {
		Project project = this.queryProjectById(updateDTO.getId());
		project.setName(updateDTO.getName());
		project.setGithubLink(updateDTO.getGithubLink());
		this.projectRepository.save(project);
		return new ProjectResponseDTO(project);
	}
	
	public ProjectListResponseDTO getProjects() {
		Set<Project> projects = new HashSet<>(this.projectRepository.findAll());
		return new ProjectListResponseDTO(projects);
	}
	
	public void deleteProjectById(Long id) {
		this.projectRepository.deleteById(id);
	}
	
	public Project queryProjectById(Long id) throws ResponseStatusException {
		Optional<Project> optnProject = this.projectRepository.findById(id);
		if (optnProject.isEmpty())
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Project with id: " + id + "does not exist");
		return optnProject.get();
	}
	
	public ProjectResponseDTO createProjectAccount(Long id, Long accountId) {
		Project project = this.queryProjectById(id);
		Account account = this.accountService.queryAccountById(accountId);
		AccountProject newAccountProject = 
				new AccountProject.Builder().account(account).project(project).build();
		this.accountProjectRepository.save(newAccountProject);
		project.getAccountProjects().add(newAccountProject);
		return new ProjectResponseDTO(project);
	}
	
	public ProjectResponseDTO updateProjectAccountIsSubscribed(Long id, Long accountid) {
		Project project = this.queryProjectById(id);
		Account account = this.accountService.queryAccountById(accountid);
		project.getAccountProjects().stream().filter(accountProject -> 
				accountProject.getProject().equals(project) && accountProject.getAccount().equals(account))
		  		.findAny()
		  		.orElse(null);
		return null;
	}
}
