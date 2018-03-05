package com.lockertracker.converter;

import java.util.LinkedList;
import java.util.List;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;
import com.lockertracker.model.LockerGUIModel.RentedByEnum;

public class LockerModelConverter {

	public static List<LockerGUIModel> convertDBtoGUI(final List<LockerDBModel> lockersFromDB, final long userId) {
		List<LockerGUIModel> lockersForGUI = new LinkedList<LockerGUIModel>();

		for (LockerDBModel lockerFromDB : lockersFromDB) {
			LockerGUIModel lockerGUIModel = new LockerGUIModel();
			lockerGUIModel.setId(lockerFromDB.getId());
			lockerGUIModel.setRented(lockerFromDB.isRented());

			boolean isRentedByThisUser = (lockerFromDB.getRentedByEmployeeId() == userId) ? true : false;
			lockerGUIModel.setRentedByLoginedUser(isRentedByThisUser);

			if (!lockerFromDB.isRented()) {
				lockerGUIModel.setRentedByEnum(RentedByEnum.NOT_RENTED);
			} else {
				RentedByEnum rentedBy = (lockerFromDB.getRentedByEmployeeId() == userId) ? RentedByEnum.RENTED_BY_YOU : RentedByEnum.RENTED_BY_OTHER;
				lockerGUIModel.setRentedByEnum(rentedBy);
			}

			lockersForGUI.add(lockerGUIModel);
		}

		return lockersForGUI;
	}

}
