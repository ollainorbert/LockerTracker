package com.lockertracker.converter;

import org.springframework.core.convert.converter.Converter;

import com.lockertracker.model.LockerDBModel;
import com.lockertracker.model.LockerGUIModel;

public class LockerModelConverter implements Converter<LockerDBModel, LockerGUIModel> {

	// ORIKA MAPPER
	@Override
	public LockerGUIModel convert(LockerDBModel arg0) {
		LockerGUIModel lockerGUIModel = new LockerGUIModel();

		lockerGUIModel.setId(arg0.getId());
		lockerGUIModel.setRented(arg0.isRented());
		lockerGUIModel.setRentedByUserId(arg0.getRentedByUserId());

		return lockerGUIModel;
	}

}
