package com.lockertracker.service.impl;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.UserDBModel;
import com.lockertracker.repository.LockerRepository;
import com.lockertracker.repository.UserRepository;
import com.lockertracker.service.exception.locker.BaseLockerException;
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

	/**
	 * We need some mock objects.
	 */
	@Before
	public void setup() {
		this.lockerRepository = mock(LockerRepository.class);
		this.lockerServiceHelper = mock(LockerServiceHelper.class);
		this.userRepository = mock(UserRepository.class);

		this.lockerServiceImpl = new LockerServiceImpl(lockerRepository, lockerServiceHelper, userRepository);
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

	// itt valami furcsasag van
	/**
	 * itt valami furcsasag van
	 * 
	 * @throws BaseLockerException
	 */
	// itt valami furcsasag van
	@Test
	public void testRentingALocker() throws BaseLockerException {
		long lockerDbModelID = 3;
		String testUserName = "testUserName";

		LockerDBModel lockerDBModel = new LockerDBModel();
		lockerDBModel.setId(lockerDbModelID);

		UserDBModel userDBModel = new UserDBModel();
		userDBModel.setId(lockerDbModelID);

		lockerServiceImpl = new LockerServiceImpl(null, lockerServiceHelper, userRepository);

		Mockito.when(lockerServiceHelper.setReservableLockerByIdInMemory(null, true, null)).thenReturn(lockerDBModel);
		Mockito.when(userRepository.findByUsername(testUserName)).thenReturn(userDBModel);
		Mockito.when(lockerRepository.save(lockerDBModel)).thenReturn(null);

		lockerServiceImpl.rentLocker(null, testUserName);
	}

}
