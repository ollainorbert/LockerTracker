package com.lockertracker.service.impl.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.repository.LockerRepository;
import com.lockertracker.service.exception.locker.LockerNotFoundException;

@Component
public class LockerServiceHelper {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public LockerDBModel setReservableLockerBy(final short id, final boolean isRenting,
			final LockerRepository lockerRepository) throws LockerNotFoundException {

		LockerDBModel lockerModel = lockerRepository.findById(id);

		if (lockerModel == null) {
			throw new LockerNotFoundException();
		} else {
			lockerModel.setRented(isRenting);
			return lockerModel;
		}
	}

	public LockerDBModel setReservableLockerBy(final String id, final boolean isRenting,
			final LockerRepository lockerRepository) throws LockerNotFoundException {

		try {
			short convertedId = Short.parseShort(id);
			LockerDBModel lockerModel = lockerRepository.findById(convertedId);
			lockerModel.setRented(isRenting);
			return lockerModel;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new LockerNotFoundException();
		}
	}

}
