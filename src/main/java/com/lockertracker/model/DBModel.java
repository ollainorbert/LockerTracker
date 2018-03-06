package com.lockertracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lockertracker.model.exception.BaseRegistrationValidationException;

@Entity
@Table
public abstract class DBModel {

	public abstract void validate() throws BaseRegistrationValidationException;

	@GeneratedValue
	@Id
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
