package com.lockertracker.service.impl.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.repository.LockerRepository;
import com.lockertracker.service.exception.locker.BaseLockerException;
import com.lockertracker.service.exception.locker.LockerAlreadyReleasedException;
import com.lockertracker.service.exception.locker.LockerAlreadyRentedException;
import com.lockertracker.service.exception.locker.LockerNotFoundException;

@Component
public class LockerServiceHelper {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public LockerDBModel setReservableLockerById(final String id, final boolean isRenting,
			final LockerRepository lockerRepository) throws BaseLockerException {
		LockerDBModel lockerDBModel = null;
		lockerDBModel = checkLockerId(id, lockerRepository);
		lockerDBModel = setReservableLockerByExistLockerModel(lockerDBModel, isRenting);
		return lockerDBModel;
	}

	/**
	 * Cannot be null. An LockerNotFoundException would be thrown, instead.
	 * 
	 * @throws LockerNotFoundException
	 * @return The existing Locker From DB
	 */
	public LockerDBModel checkLockerId(final String id, final LockerRepository lockerRepository)
			throws LockerNotFoundException {

		try {
			short convertedId = Short.parseShort(id);
			LockerDBModel lockerModel = lockerRepository.findById(convertedId);
			if (lockerModel == null) {
				throw new LockerNotFoundException();
			} else {
				return lockerModel;
			}
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			throw new LockerNotFoundException();
		}

	}

	public LockerDBModel setReservableLockerByExistLockerModel(LockerDBModel lockerModel, final boolean isRenting)
			throws BaseLockerException {

		try {
			if (lockerModel.isRented() != isRenting) {
				lockerModel.setRented(isRenting);
				return lockerModel;
			} else {
				if (lockerModel.isRented()) {
					throw new LockerAlreadyRentedException();
				} else {
					throw new LockerAlreadyReleasedException();
				}
			}
		} catch (BaseLockerException e) {
			logger.error(e.toString());
			throw e;
		}
	}

}
