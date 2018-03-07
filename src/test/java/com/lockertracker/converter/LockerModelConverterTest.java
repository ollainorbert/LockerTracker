package com.lockertracker.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	public void setup() throws Exception {
		lockerModelConverter = new LockerModelConverter();
	}

	@Test
	public void testConvert() throws Exception {
		long lockerId = 0;
		boolean isRented = true;
		long rentedByEmployeeId = 0;

		LockerDBModel lockerDBModel = new LockerDBModel();
		lockerDBModel.setId(lockerId);
		lockerDBModel.setRented(isRented);
		lockerDBModel.setRentedByEmployeeId(rentedByEmployeeId);

		LockerGUIModel lockerGUIModel = lockerModelConverter.convert(lockerDBModel);

		assertNotNull(lockerGUIModel);
		assertEquals(lockerDBModel.getId(), lockerGUIModel.getId());
		assertEquals(lockerDBModel.isRented(), lockerGUIModel.isRented());
		assertEquals(lockerDBModel.getRentedByEmployeeId(), lockerGUIModel.getRentedByEmployeeId());
	}

}
