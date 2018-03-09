package com.lockertracker.service.impl;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lockertracker.repository.LockerRepository;
import com.lockertracker.repository.UserRepository;
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

	@Test
	public void testX() {

	}
}
