package com.aldemoralinator.lemonade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aldemoralinator.lemonade.model.entity.AccountProject;
import com.aldemoralinator.lemonade.model.key.AccountProjectKey;

public interface AccountProjectRepository extends JpaRepository<AccountProject, AccountProjectKey>{
	
}
