package com.lockertracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Lockerrentals", uniqueConstraints = { @UniqueConstraint(columnNames = { "lockerId", "userId" }) })
public class LockerRentalModel {

	@GeneratedValue
	@Id
	private long id;

	@Column(nullable = false)
	private short lockerId;

	@Column(nullable = false)
	private long userId;

	@Column(nullable = false)
	private Date createdAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public short getLockerId() {
		return lockerId;
	}

	public void setLockerId(short lockerId) {
		this.lockerId = lockerId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
