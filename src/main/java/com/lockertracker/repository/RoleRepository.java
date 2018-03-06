package com.lockertracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lockertracker.model.RoleDBModel;

public interface RoleRepository extends JpaRepository<RoleDBModel, Short> {

	public RoleDBModel findByRole(String roleName);
}
