package com.aldemoralinator.lemonade.model.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table( name="project" )
@ToString
@EqualsAndHashCode
public class Project {
	
	@Getter
	private Long id;
	
	@Getter
	private String name;
	
	@Getter
	private String githubLink;
	
	@Getter
	private Date created_at;
	
	@Getter
	private Date updated_at;
	
	@OneToMany(mappedBy = "project")
    Set<AccountProject> accountProjects;
	
}
