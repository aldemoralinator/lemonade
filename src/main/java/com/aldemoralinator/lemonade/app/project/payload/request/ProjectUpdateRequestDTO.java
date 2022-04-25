package com.aldemoralinator.lemonade.app.project.payload.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper=true)
public class ProjectUpdateRequestDTO extends ProjectCreateRequestDTO {
	
	@Getter
	@Setter
	private Long id; 
}
