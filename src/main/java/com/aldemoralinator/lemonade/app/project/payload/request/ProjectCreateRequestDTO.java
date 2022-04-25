package com.aldemoralinator.lemonade.app.project.payload.request;

import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ProjectCreateRequestDTO {
	
	@Getter
	@Setter
	@NotEmpty
	private String name;
	
	@Getter
	@Setter
	@NotEmpty
	private String githubLink;

	public ProjectCreateRequestDTO() { }
}
