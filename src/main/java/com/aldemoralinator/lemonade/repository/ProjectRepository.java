package com.aldemoralinator.lemonade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aldemoralinator.lemonade.model.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	Boolean existsByName(String name);
}
