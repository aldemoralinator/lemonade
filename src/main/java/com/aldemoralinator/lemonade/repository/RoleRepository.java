package com.aldemoralinator.lemonade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aldemoralinator.lemonade.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
