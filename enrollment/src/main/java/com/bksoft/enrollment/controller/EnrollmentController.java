/**
 * 
 */
package com.bksoft.enrollment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bksoft.enrollment.model.Address;
import com.bksoft.enrollment.model.Student;

/**
 * @author BRAJENDRA SINGH
 */
@RestController
public class EnrollmentController {

	private final static Log log = LogFactory.getLog(EnrollmentController.class);

	@RequestMapping(value = "/student")
	public Student getStudentById(@RequestParam(name = "id", required = false) String id) {
		Student s = null;
		try {
			Address a = new Address("Delhi", 201301, "New Delhi", "India");
			s = new Student("BKSOFT", "2019", a);
			log.info("response: " + s);
		} catch (Exception e) {
			log.error("StackTrace ", e);
		}
		return s;
	}
}
