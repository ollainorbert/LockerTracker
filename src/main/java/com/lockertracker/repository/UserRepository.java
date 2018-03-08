package com.lockertracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lockertracker.model.UserDBModel;

@Repository
public interface UserRepository extends JpaRepository<UserDBModel, Long> {

	public UserDBModel findByUsername(String username);

}
