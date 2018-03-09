package com.lockertracker.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;
import com.lockertracker.model.UserDBModel;
import com.lockertracker.repository.LockerRepository;
import com.lockertracker.repository.UserRepository;
import com.lockertracker.service.exception.locker.BaseLockerException;
import com.lockertracker.service.exception.locker.UserNotFoundException;
import com.lockertracker.service.impl.helper.LockerServiceHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockerServiceImplTest {

	private LockerServiceImpl lockerServiceImpl;

	@Mock
	private LockerRepository lockerRepository;

	@Mock
	private LockerServiceHelper lockerServiceHelper;

	@Mock
	private UserRepository userRepository;

	private LockerDBModel lockerDBModel;
	private UserDBModel userDBModel;
	private long testLockerDbModelID = 3;
	private String testUserName = "testUserName";
	private String testId = "testId";

	/**
	 * We need some mock objects.
	 */
	@Before
	public void setup() {
		this.lockerRepository = mock(LockerRepository.class);
		this.lockerServiceHelper = mock(LockerServiceHelper.class);
		this.userRepository = mock(UserRepository.class);
		this.lockerServiceImpl = new LockerServiceImpl(lockerRepository, lockerServiceHelper, userRepository);

		this.lockerDBModel = new LockerDBModel();
		this.userDBModel = new UserDBModel();
		this.testLockerDbModelID = 3;
		this.testUserName = "testUserName";
		this.testId = "testId";
	}

	// @Test
	// public void testfindAll() {
	// Mockito.when(lockerRepository.findAll()).thenReturn(new Ar)
	// }

	// @Test
	// public void testX() {
	// List<LockerDBModel> lockerDBModels = new ArrayList<LockerDBModel>();
	//
	// LockerDBModel lockerDBModel1 = new LockerDBModel();
	// lockerDBModel1.setRented(true);
	// lockerDBModels.add(lockerDBModel1);
	//
	// LockerDBModel lockerDBModel2 = new LockerDBModel();
	// lockerDBModel2.setRented(false);
	// lockerDBModels.add(lockerDBModel2);
	//
	// Mockito.when(lockerRepository.findAll()).thenReturn(lockerDBModels);
	// // Mockito.when(lockerRepository.save(lockerDBModel1)).then;
	//
	// }

	@Test
	public void testRentingALocker() throws BaseLockerException {
		lockerDBModel.setId(testLockerDbModelID);
		userDBModel.setId(testLockerDbModelID);

		Mockito.when(lockerServiceHelper.setReservableLockerByIdInMemory(testId, true, lockerRepository))
				.thenReturn(lockerDBModel);
		Mockito.when(userRepository.findByUsername(testUserName)).thenReturn(userDBModel);
		Mockito.when(lockerRepository.save(lockerDBModel)).thenReturn(null);

		lockerServiceImpl.rentLocker(testId, testUserName);
	}

	@Test
	public void testReleasingALocker() throws BaseLockerException {
		lockerDBModel.setId(testLockerDbModelID);

		Mockito.when(lockerServiceHelper.setReservableLockerByIdInMemory(testId, true, lockerRepository))
				.thenReturn(lockerDBModel);
		Mockito.when(lockerRepository.save(lockerDBModel)).thenReturn(null);

		lockerServiceImpl.releaseLockerById(testId);
	}

	@Test
	public void testAllLockerWithUserBelongs() throws UserNotFoundException {
		userDBModel.setId(testLockerDbModelID);
		List<LockerDBModel> lockerDBModels = new ArrayList<LockerDBModel>();

		Mockito.when(userRepository.findByUsername(testUserName)).thenReturn(userDBModel);
		Mockito.when(lockerRepository.findAll()).thenReturn(lockerDBModels);

		List<LockerGUIModel> lockerGUIModels = lockerServiceImpl.getAllLockerWithUserBelongs(testUserName);

		assertEquals(lockerDBModels.size(), lockerGUIModels.size());
	}

	@Test(expected = UserNotFoundException.class)
	public void testAllLockerWithUserBelongsIfUsernameNotExist() throws UserNotFoundException {
		userDBModel.setId(testLockerDbModelID);

		Mockito.when(userRepository.findByUsername(testUserName)).thenReturn(null);

		lockerServiceImpl.getAllLockerWithUserBelongs(testUserName);
	}

}
