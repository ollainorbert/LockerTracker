package com.lockertracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lockertracker.model.LockerRentalModel;

@Repository
public interface LockerRentalRepository extends JpaRepository<LockerRentalModel, Long> {

}
