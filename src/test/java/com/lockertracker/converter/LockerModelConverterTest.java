package com.lockertracker.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LockerModelConverterTest {

	@InjectMocks
	private LockerModelConverter lockerModelConverter;

	@Test
	public void testConvertWhenTheUserIsTheOwnerToo() {
		long lockerId = 0;
		long rentedByUserId = 0;

		LockerDBModel lockerDBModel = new LockerDBModel();
		lockerDBModel.setId(lockerId);
		lockerDBModel.setRentedByUserId(rentedByUserId);

		LockerGUIModel lockerGUIModel = lockerModelConverter.convert(lockerDBModel);

		assertNotNull(lockerGUIModel);
		assertEquals(lockerDBModel.getId(), lockerGUIModel.getId());
		if (lockerDBModel.getRentedByUserId() == null) {
			assertNull(lockerGUIModel.getRentedByUserId());
		} else {
			assertEquals((long) lockerDBModel.getRentedByUserId(), (long) lockerGUIModel.getRentedByUserId());
		}

	}

	@Test
	public void testConvertWhenTheUserIsNotTheOwner() {
		long lockerId = 0;
		long rentedByUserId = 1;

		LockerDBModel lockerDBModel = new LockerDBModel();
		lockerDBModel.setId(lockerId);
		lockerDBModel.setRentedByUserId(rentedByUserId);

		LockerGUIModel lockerGUIModel = lockerModelConverter.convert(lockerDBModel);

		assertNotNull(lockerGUIModel);
		assertEquals(lockerDBModel.getId(), lockerGUIModel.getId());
		if (lockerDBModel.getRentedByUserId() == null) {
			assertNull(lockerGUIModel.getRentedByUserId());
		} else {
			assertEquals((long) lockerDBModel.getRentedByUserId(), (long) lockerGUIModel.getRentedByUserId());
		}

	}

}
