package com.bksoft.enrollment.model;

public class Address {

	private String city;
	private int pincode;
	private String state;
	private String counrty;

	public Address(String city, int pincode, String state, String counrty) {
		super();
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.counrty = counrty;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounrty() {
		return counrty;
	}

	public void setCounrty(String counrty) {
		this.counrty = counrty;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", pincode=" + pincode + ", state=" + state + ", counrty=" + counrty + "]";
	}

}
