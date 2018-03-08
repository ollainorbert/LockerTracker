package com.lockertracker.service.impl.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockerServiceHelperTest {

	@Autowired
	private LockerServiceHelper lockerServiceHelper;

	@Test
	public void testConvertDBtoGUI() {
		long loginedUserID = 1;

		List<LockerDBModel> lockerDBModels = new ArrayList<LockerDBModel>();

		LockerDBModel lockerDBModel1 = new LockerDBModel();
		lockerDBModel1.setId(0);
		lockerDBModel1.setRented(true);
		lockerDBModel1.setRentedByUserId(0);
		lockerDBModels.add(lockerDBModel1);

		LockerDBModel lockerDBModel2 = new LockerDBModel();
		lockerDBModel2.setId(1);
		lockerDBModel2.setRented(false);
		lockerDBModel2.setRentedByUserId(1);
		lockerDBModels.add(lockerDBModel2);

		List<LockerGUIModel> lockersForGUI = lockerServiceHelper.convertDBtoGUI(lockerDBModels, loginedUserID);

		assertNotNull(lockersForGUI);
		assertEquals(lockerDBModels.size(), lockersForGUI.size());

		for (int i = 0; i < lockerDBModels.size(); ++i) {
			long rentedByEmployeeId = lockersForGUI.get(i).getRentedByUserId();
			if (lockerDBModels.get(i).getRentedByUserId() == loginedUserID) {
				assertEquals(loginedUserID, rentedByEmployeeId);
			} else {
				assertNotEquals(loginedUserID, rentedByEmployeeId);
			}
		}
	}
}
