package com.bksoft.career.model;

public class WorkHistory {

	private String company;
	private String experience;

	public WorkHistory(String company, String experience) {
		this.company = company;
		this.experience = experience;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "WorkHistory [company=" + company + ", experience=" + experience + "]";
	}

}
