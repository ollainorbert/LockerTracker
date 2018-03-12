package com.lockertracker.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockerModelConverterTest {
	private LockerModelConverter lockerModelConverter;

	@Before
	public void setup() {
		lockerModelConverter = new LockerModelConverter();
	}

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
