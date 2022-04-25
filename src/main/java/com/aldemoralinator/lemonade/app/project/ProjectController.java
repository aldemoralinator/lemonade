package com.aldemoralinator.lemonade.app.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aldemoralinator.lemonade.app.account.payload.response.AccountResponseDTO;
import com.aldemoralinator.lemonade.app.project.payload.request.ProjectCreateRequestDTO;
import com.aldemoralinator.lemonade.app.project.payload.request.ProjectUpdateRequestDTO;
import com.aldemoralinator.lemonade.app.project.payload.response.ProjectListResponseDTO;
import com.aldemoralinator.lemonade.app.project.payload.response.ProjectResponseDTO;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@PostMapping("")
	public ResponseEntity<?> createProject(@RequestBody ProjectCreateRequestDTO reqDTO) {
		ProjectResponseDTO res = this.projectService.createProject(reqDTO);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getProjects() {
		ProjectListResponseDTO res = this.projectService.getProjects();
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectById(@PathVariable Long id) throws Exception {
		ProjectResponseDTO res = this.projectService.getProjectById(id);
		return ResponseEntity.ok(res);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody ProjectUpdateRequestDTO reqDTO) {
		ProjectResponseDTO res = this.projectService.updateProjectById(reqDTO);
		return ResponseEntity.ok(res);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id) {
		this.projectService.deleteProjectById(id);
		return ResponseEntity.ok("Project with Id: " + id + "Deleted successfully");
	}
	
	@PostMapping("/{id}/account/{accountId}")
	public ResponseEntity<?> createProjectAccount(
			@PathVariable("id") Long id, @PathVariable("accountId") Long accountId) {
		return null;
	}
	
	@PatchMapping("/{id}/account/{accountId}")
	public ResponseEntity<?> updateProjectAccountIsSubscribed(
			@PathVariable("id") Long id, @PathVariable("accountId") Long accountId) {
		return null;
	}
	
	@DeleteMapping("/{id}/account/{accountId}")
	public ResponseEntity<?> deleteProjectAccount(
			@PathVariable("id") Long id, @PathVariable("accountId") Long accountId) {
		return null;
	}
}
