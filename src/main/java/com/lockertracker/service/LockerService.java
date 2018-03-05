package com.lockertracker.service;

import java.util.List;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;
import com.lockertracker.service.exception.locker.BaseLockerException;
import com.lockertracker.service.exception.locker.EmployeeNotFoundException;

public interface LockerService {

	public List<LockerDBModel> findAll();

	public List<LockerGUIModel> getAllLockerWithUserBelongs(final String username) throws EmployeeNotFoundException;

	public void rentLocker(final String id, final String username) throws BaseLockerException;

	public void releaseLockerById(final short id) throws BaseLockerException;

	public void releaseAllLocker();
}
