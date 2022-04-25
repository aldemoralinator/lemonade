package com.aldemoralinator.lemonade.app.project.payload.response;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.aldemoralinator.lemonade.app.account.payload.response.RoleResponseDTO;
import com.aldemoralinator.lemonade.exception.ExceptionMessageBuilder;
import com.aldemoralinator.lemonade.model.entity.Project;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ProjectListResponseDTO {
	
	@Getter
	private Set<ProjectResponseDTO> projects = new HashSet<>();
	
	public ProjectListResponseDTO(Set<Project> projects) {
		this.projects = projects.stream().map(
				project -> new ProjectResponseDTO(project)).collect(Collectors.toSet());
	}
}
