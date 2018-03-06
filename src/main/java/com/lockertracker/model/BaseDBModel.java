package com.lockertracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.lockertracker.model.exception.BaseRegistrationValidationException;

@MappedSuperclass
public abstract class BaseDBModel {

	public abstract void validate() throws BaseRegistrationValidationException;

	@GeneratedValue
	@Id
	private long id;

	@Column(updatable = false, nullable = false)
	private Date createdAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	private void beforeInsert() throws BaseRegistrationValidationException {
		this.setCreatedAt(new Date());

		this.validate();
	}
}
