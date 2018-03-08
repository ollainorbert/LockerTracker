package com.lockertracker.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;
import com.lockertracker.model.UserDBModel;
import com.lockertracker.repository.LockerRepository;
import com.lockertracker.repository.UserRepository;
import com.lockertracker.service.LockerService;
import com.lockertracker.service.exception.locker.BaseLockerException;
import com.lockertracker.service.exception.locker.UserNotFoundException;
import com.lockertracker.service.impl.helper.LockerServiceHelper;

@Service
public class LockerServiceImpl implements LockerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LockerRepository lockerRepository;
	private LockerServiceHelper lockerServiceHelper;
	private UserRepository userRepository;

	@Autowired
	public LockerServiceImpl(LockerRepository lockerRepository, LockerServiceHelper lockerServiceHelper,
			UserRepository userRepository) {
		this.lockerRepository = lockerRepository;
		this.lockerServiceHelper = lockerServiceHelper;
		this.userRepository = userRepository;
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

		LockerDBModel lockerModel = lockerServiceHelper.setReservableLockerById(id, true, lockerRepository);

		UserDBModel userDBModel = userRepository.findByUsername(username);
		lockerModel.setRentedByUserId(userDBModel.getId());

		lockerRepository.save(lockerModel);
		logger.info("Locker rent success!");
	}

	@Override
	public void releaseLockerById(final String id) throws BaseLockerException {
		LockerDBModel lockerModel = lockerServiceHelper.setReservableLockerById(id, false, lockerRepository);

		lockerRepository.save(lockerModel);
		logger.info("Locker release success!");
	}

	@Override
	public List<LockerGUIModel> getAllLockerWithUserBelongs(final String username) throws UserNotFoundException {
		logger.info("Logined (unique) username: " + username);

		UserDBModel userDBModel = userRepository.findByUsername(username);
		if (userDBModel == null) {
			throw new UserNotFoundException();
		}

		List<LockerDBModel> lockersFromDB = lockerRepository.findAll();
		long userId = userDBModel.getId();

		return lockerServiceHelper.convertDBtoGUI(lockersFromDB, userId);
	}

}
