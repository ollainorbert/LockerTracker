package com.lockertracker.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LockerGUIModel {
	public enum RentedByEnum {
		NOT_RENTED, RENTED_BY_OTHER, RENTED_BY_YOU
	}

	private long id;
	private RentedByEnum rentedByEnum;
	private Long rentedByUserId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RentedByEnum getRentedByEnum() {
		return rentedByEnum;
	}

	public void setRentedByEnum(RentedByEnum rentedByEnum) {
		this.rentedByEnum = rentedByEnum;
	}

	public Long getRentedByUserId() {
		return rentedByUserId;
	}

	public void setRentedByUserId(Long rentedByUserId) {
		this.rentedByUserId = rentedByUserId;
	}

	public void setRentingAttributesBy(long loginedUserID) {
		if (this.getRentedByUserId() == null) {
			this.setRentedByEnum(RentedByEnum.NOT_RENTED);
		} else {
			RentedByEnum rentedBy = (this.getRentedByUserId() == loginedUserID) ? RentedByEnum.RENTED_BY_YOU
					: RentedByEnum.RENTED_BY_OTHER;

			this.setRentedByEnum(rentedBy);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
