package com.bksoft.career.model;

import java.util.List;

public class Applicant {

	private String name;
	private Address address;
	private Qualification qualifications;
	private WorkHistory workHistory;

	public Applicant(String name, Address address, Qualification qualifications, WorkHistory workHistory) {
		this.name = name;
		this.address = address;
		this.qualifications = qualifications;
		this.workHistory = workHistory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Qualification getQualifications() {
		return qualifications;
	}

	public void setQualifications(Qualification qualifications) {
		this.qualifications = qualifications;
	}

	public WorkHistory getWorkHistory() {
		return workHistory;
	}

	public void setWorkHistory(WorkHistory workHistory) {
		this.workHistory = workHistory;
	}

	@Override
	public String toString() {
		return "Applicant [name=" + name + ", address=" + address + ", qualifications=" + qualifications
				+ ", workHistory=" + workHistory + "]";
	}

}
