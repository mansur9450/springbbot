package com.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Role;
import com.springboot.entity.User;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);

}
