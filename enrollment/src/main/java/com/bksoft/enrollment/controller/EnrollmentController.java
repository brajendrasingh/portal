/**
 * 
 */
package com.bksoft.enrollment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bksoft.enrollment.model.PaymentDetails;
import com.bksoft.enrollment.model.Student;
import com.bksoft.enrollment.services.EnrollmentService;

/**
 * @author BRAJENDRA SINGH
 */
@RestController
public class EnrollmentController {

	private final static Log log = LogFactory.getLog(EnrollmentController.class);

	@Autowired
	private EnrollmentService enrollmentService;

	@RequestMapping(value = "/student")
	public Student getStudentById(@RequestParam(name = "id", required = false) String id) {
		Student s = null;
		try {
			s = enrollmentService.getStudentById("45645");
			log.info("response: " + s);
		} catch (Exception e) {
			log.error("StackTrace ", e);
		}
		return s;
	}

	@RequestMapping(value = "/feeByCoarse")
	public String getFeesByCoarse(@RequestParam(name = "coarse", required = false) String coarse) {
		String fee = null;
		try {
			fee = enrollmentService.getFeesByCoarse("45645");
			log.info("response: " + fee);
		} catch (Exception e) {
			log.error("StackTrace ", e);
		}
		return fee;
	}

	@RequestMapping(value = "/fee")
	public ResponseEntity<PaymentDetails> getFeeDetailsById(@RequestParam(name = "id", required = false) String id) {

		ResponseEntity<PaymentDetails> pd = null;
		try {
			pd = enrollmentService.getFeeDetailsById("45645");
			log.info("response: " + pd);
		} catch (Exception e) {
			log.error("StackTrace ", e);
		}
		return pd;
	}

}
