package com.lockertracker.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lockertracker.converter.LockerModelConverter;
import com.lockertracker.model.EmployeeModel;
import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;
import com.lockertracker.repository.EmployeeRepository;
import com.lockertracker.repository.LockerRepository;
import com.lockertracker.service.LockerService;
import com.lockertracker.service.exception.locker.BaseLockerException;
import com.lockertracker.service.exception.locker.EmployeeNotFoundException;
import com.lockertracker.service.impl.helper.LockerServiceHelper;

@Service
public class LockerServiceImpl implements LockerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LockerRepository lockerRepository;
	private EmployeeRepository employeeRepository;
	private LockerServiceHelper lockerServiceHelper;

	@Autowired
	public LockerServiceImpl(LockerRepository lockerRepository, EmployeeRepository employeeRepository,
			LockerServiceHelper lockerServiceHelper) {
		this.lockerRepository = lockerRepository;
		this.employeeRepository = employeeRepository;
		this.lockerServiceHelper = lockerServiceHelper;
	}

	@Override
	public List<LockerDBModel> findAll() {
		return lockerRepository.findAll();
	}

	@Override
	public void releaseAllLocker() {
		List<LockerDBModel> allLockerModel = this.lockerRepository.findAll();

		for (LockerDBModel lockerModel : allLockerModel) {
			lockerModel.setRented(false);
			lockerRepository.save(lockerModel);
		}
	}

	@Override
	public void rentLocker(final String id, final String username) throws BaseLockerException {
		logger.info("Rent starting for, id: " + id + " locker.");
		logger.info("Logined (unique) username: " + username + ".");

		LockerDBModel lockerModel = lockerServiceHelper.setReservableLockerBy(id, true, lockerRepository);

		EmployeeModel employeeModel = employeeRepository.findByUsername(username);
		lockerModel.setRentedByEmployeeId(employeeModel.getId());

		lockerRepository.save(lockerModel);
		logger.info("Locker rent success!");
	}

	@Override
	public void releaseLockerById(final short id) throws BaseLockerException {
		LockerDBModel lockerModel = lockerServiceHelper.setReservableLockerBy(id, false, lockerRepository);

		lockerRepository.save(lockerModel);
		logger.info("Locker release success!");
	}

	@Override
	public List<LockerGUIModel> getAllLockerWithUserBelongs(final String username) throws EmployeeNotFoundException {
		logger.info("Logined (unique) username: " + username);

		EmployeeModel employeeModel = employeeRepository.findByUsername(username);
		if (employeeModel == null) {
			throw new EmployeeNotFoundException();
		}

		List<LockerDBModel> lockersFromDB = lockerRepository.findAll();
		long employeeId = employeeModel.getId();

		return LockerModelConverter.convertDBtoGUI(lockersFromDB, employeeId);
	}

}
