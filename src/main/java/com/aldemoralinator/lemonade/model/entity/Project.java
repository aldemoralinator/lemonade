package com.aldemoralinator.lemonade.model.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aldemoralinator.lemonade.exception.ExceptionMessageBuilder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table( name="project" )
@ToString
@EqualsAndHashCode
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter
	private Long id;
	
	@Getter
	@Setter
	@Column(name="name", length=255, nullable=false, unique=false)
	private String name;
	
	@Getter
	@Setter
	@Column(name="github_link", length=255, nullable=false, unique=false)
	private String githubLink;
	
	@Getter
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="created_by", nullable=false)
	private Account createdBy; 
	
	@Getter
	@Column(name="created_at", nullable=false)
	private Date createdAt;
	
	@Getter
	@Column(name="updated_at", nullable=false)
	private Date updatedAt;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "project")
    private Set<AccountProject> accountProjects;
	
	public Project(Builder builder) {
		
	}
	
	public static class Builder {
		
		@Getter
		private String name;
		
		@Getter
		private String githubLink;
		
		@Getter
		private Account createdBy;
		
		public Project build() {
			this.validate();
			return new Project(this);
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder githubLink(String githubLink) {
			this.githubLink = githubLink;
			return this;
		}
		
		public Builder createdBy(Account createdBy) {
			this.createdBy = createdBy;
			return this;
		}
		
		private void validate() {
			ExceptionMessageBuilder emb = new ExceptionMessageBuilder();
        	if (this.name == null) emb.append("Name must not be null");
        	if (this.githubLink == null) emb.append("Github Link must not be null");
        	emb.throwExceptionIfNotEmpty();
		}
		
	}
	
}
