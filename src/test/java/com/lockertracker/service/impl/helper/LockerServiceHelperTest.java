package com.lockertracker.service.impl.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;
import com.lockertracker.repository.LockerRepository;
import com.lockertracker.service.exception.locker.BaseLockerException;
import com.lockertracker.service.exception.locker.LockerAlreadyReleasedException;
import com.lockertracker.service.exception.locker.LockerAlreadyRentedException;
import com.lockertracker.service.exception.locker.LockerNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockerServiceHelperTest {

	@Autowired
	private LockerServiceHelper lockerServiceHelper;

	@Mock
	private LockerRepository lockerRepository;

	private LockerDBModel lockerModel;
	private String anIdAsString;
	private long anIdAsNumber;

	@Before
	public void setup() {
		this.lockerModel = new LockerDBModel();
		this.anIdAsString = "5";
		this.anIdAsNumber = Long.parseLong(anIdAsString);

		this.lockerRepository = mock(LockerRepository.class);
	}

	/**
	 * Check locker id, but it will be a not number string.
	 * 
	 * @throws LockerNotFoundException
	 */
	@Test(expected = LockerNotFoundException.class)
	public void testCheckExistentLockerWithNaNString() throws LockerNotFoundException {
		lockerServiceHelper.checkExistanceLockerById("notANumber", lockerRepository);
	}

	/**
	 * Check locker id, but it won't exist.
	 * 
	 * @throws LockerNotFoundException
	 */
	@Test(expected = LockerNotFoundException.class)
	public void testCheckNonExistentLockerId() throws LockerNotFoundException {
		Mockito.when(lockerRepository.findById(anIdAsNumber)).thenReturn(null);
		lockerServiceHelper.checkExistanceLockerById(anIdAsString, lockerRepository);
	}

	/**
	 * Check locker id, and it's exist, so won't be throwed any exception.
	 * 
	 * @throws LockerNotFoundException
	 */
	@Test
	public void testCheckExistentLocker() throws LockerNotFoundException {
		Mockito.when(lockerRepository.findById(anIdAsNumber)).thenReturn(new LockerDBModel());
		lockerServiceHelper.checkExistanceLockerById(anIdAsString, lockerRepository);
	}

	@Test(expected = LockerAlreadyRentedException.class)
	public void testLockerRentingByExistLockerModelThatAlreadyRented() throws BaseLockerException {
		boolean isRenting = true;
		lockerModel.setRented(isRenting);

		lockerServiceHelper.setReservableLockerByExistLockerModel(lockerModel, isRenting);
	}

	@Test(expected = LockerAlreadyReleasedException.class)
	public void testLockerReleasingByExistLockerModelThatAlreadyReleased() throws BaseLockerException {
		boolean isRenting = false;
		lockerModel.setRented(isRenting);

		lockerServiceHelper.setReservableLockerByExistLockerModel(lockerModel, isRenting);
	}

	@Test
	public void testLockerRentingByExistLockerModel() throws BaseLockerException {
		boolean isRenting = true;
		lockerModel.setRented(!isRenting);

		lockerServiceHelper.setReservableLockerByExistLockerModel(lockerModel, isRenting);
	}

	@Test
	public void testLockerReleasingByExistLockerModel() throws BaseLockerException {
		boolean isRenting = false;
		lockerModel.setRented(!isRenting);

		lockerServiceHelper.setReservableLockerByExistLockerModel(lockerModel, isRenting);
	}

	@Test
	public void testAFullRenting() throws BaseLockerException {
		boolean isRenting = true;

		lockerModel.setRented(!isRenting);
		Mockito.when(lockerRepository.findById(anIdAsNumber)).thenReturn(lockerModel);

		lockerServiceHelper.setReservableLockerByIdInMemory(anIdAsString, isRenting, lockerRepository);
	}

	/**
	 * Test the converting: for beign not null, lists size must be the same, check
	 * both list id's, if one of them is rented by loginedUserId,
	 */
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
			long rentedByUserId = lockersForGUI.get(i).getRentedByUserId();
			if (lockerDBModels.get(i).getRentedByUserId() == loginedUserID) {
				assertEquals(loginedUserID, rentedByUserId);
			} else {
				assertNotEquals(loginedUserID, rentedByUserId);
			}
		}
	}

}
