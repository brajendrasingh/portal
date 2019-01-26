package com.bksoft.career.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bksoft.career.model.Address;
import com.bksoft.career.model.Applicant;
import com.bksoft.career.model.Qualification;
import com.bksoft.career.model.WorkHistory;

@RestController
public class ApplyController {

	private final static Log log = LogFactory.getLog(ApplyController.class);

	@RequestMapping(value = "/applicant")
	public Applicant getApplicantById() {

		Applicant applicant = null;
		try {
			Address add = new Address("Delhi", 201301, "New Delhi", "India");
			Qualification ql = new Qualification("B.Tech");
			List<Qualification> list = new ArrayList<Qualification>();
			list.add(ql);

			WorkHistory wh = new WorkHistory("XYZ", "10 Year");
			applicant = new Applicant("BKSOFT", add, ql, wh);
			log.info("response: " + applicant);
		} catch (Exception e) {
			log.error("StackTrace ", e);
		}

		return applicant;

	}
}
