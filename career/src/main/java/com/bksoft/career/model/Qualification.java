package com.bksoft.career.model;

import java.io.Serializable;

public class Qualification {

	private String course;

	public Qualification(String course) {
		this.course = course;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Qualification [course=" + course + "]";
	}

}
