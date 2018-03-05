package com.lockertracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lockertracker.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Short> {

	public RoleModel findByRole(String roleName);
}
