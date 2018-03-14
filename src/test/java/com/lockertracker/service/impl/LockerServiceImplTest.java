package com.lockertracker.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;
import com.lockertracker.model.UserDBModel;
import com.lockertracker.repository.LockerRepository;
import com.lockertracker.repository.UserRepository;
import com.lockertracker.service.exception.locker.BaseLockerException;
import com.lockertracker.service.exception.locker.UserNotFoundException;
import com.lockertracker.service.impl.helper.LockerServiceHelper;

@RunWith(MockitoJUnitRunner.class)
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
	private long testLockerDbModelID;
	private Long testLockerDbModelUserId;
	private String testUserName;
	private String testId;

	@Before
	public void setup() {
		this.lockerServiceImpl = new LockerServiceImpl(lockerRepository, lockerServiceHelper, userRepository);

		this.lockerDBModel = new LockerDBModel();
		this.userDBModel = new UserDBModel();
		this.testLockerDbModelID = 3;
		this.testLockerDbModelUserId = (long) 3;
		this.testUserName = "testUserName";
		this.testId = "testId";
	}

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
		lockerDBModel.setRentedByUserId(testLockerDbModelUserId);

		Mockito.when(lockerServiceHelper.setReservableLockerByIdInMemory(testId, false, lockerRepository))
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
