package com.lockertracker.converter;

import java.util.LinkedList;
import java.util.List;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;

public class LockerModelConverter {

	public static List<LockerGUIModel> convertDBtoGUI(final List<LockerDBModel> lockersFromDB, final long userId) {
		List<LockerGUIModel> lockersForGUI = new LinkedList<LockerGUIModel>();

		for (LockerDBModel lockerFromDB : lockersFromDB) {
			LockerGUIModel lockerGUIModel = new LockerGUIModel();
			lockerGUIModel.setId(lockerFromDB.getId());
			lockerGUIModel.setRented(lockerFromDB.isRented());

			boolean isRentedByThisUser = (lockerFromDB.getRentedByEmployeeId() == userId) ? true : false;
			lockerGUIModel.setRentedByLoginedUser(isRentedByThisUser);

			lockersForGUI.add(lockerGUIModel);
		}

		return lockersForGUI;
	}

}
