package com.cg.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
	// Fields
	@Id
	long addressId;
	String doorNo;
	String streetName;
	String city;

	// Constructors
	public Address() {
	}

	public Address(long addressId, String doorNo, String streetName, String city) {
		super();
		this.addressId = addressId;
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.city = city;
	}

	// Getters & Setters
	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// ToString
	@Override
	public String toString() {
		return "Address [AddressId=" + addressId + ", DoorNo=" + doorNo + ", StreetName=" + streetName + ", City="
				+ city + "]";
	}

}
