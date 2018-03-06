package com.lockertracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lockertracker.model.EmployeeDBModel;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDBModel, Long> {

	public EmployeeDBModel findByUsername(String username);

}
