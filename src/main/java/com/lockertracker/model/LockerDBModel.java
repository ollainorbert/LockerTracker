package com.lockertracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Lockers")
public class LockerDBModel extends BaseDBModel {

	/**
	 * Cannot be a foreign key, because if no one owns it, the value is null, and
	 * any user's id cannot be null.
	 */
	@Column(nullable = true)
	private Long rentedByUserId;

	public Long getRentedByUserId() {
		return rentedByUserId;
	}

	public void setRentedByUserId(Long rentedByUserId) {
		this.rentedByUserId = rentedByUserId;
	}

}
