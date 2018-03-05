package com.lockertracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lockertracker.model.LockerDBModel;

@Repository
public interface LockerRepository extends JpaRepository<LockerDBModel, Short> {

	public LockerDBModel findById(short id);

}
