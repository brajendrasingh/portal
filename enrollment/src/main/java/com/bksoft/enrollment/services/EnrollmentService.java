package com.bksoft.enrollment.services;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bksoft.enrollment.model.Address;
import com.bksoft.enrollment.model.PaymentDetails;
import com.bksoft.enrollment.model.Student;

@Service
public class EnrollmentService {

	private final static Log log = LogFactory.getLog(EnrollmentService.class);

	@Autowired
	private RestTemplate restTemplate;

	public Student getStudentById(String id) {
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

	public String getFeesByCoarse(String coarse) {
		String fee = null;
		String url = "http://localhost:8092/feeByCoarse";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		try {
			fee = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

			log.info("response: " + fee);
		} catch (Exception e) {
			log.error("StackTrace ", e);
		}
		return fee;
	}

	public ResponseEntity<PaymentDetails> getFeeDetailsById(String id) {

		ResponseEntity<PaymentDetails> pd = null;
		String url = "http://localhost:8092/paymentDetails";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		try {
			pd = restTemplate.exchange(url, HttpMethod.GET, entity, PaymentDetails.class);

			log.info("response: " + pd);
		} catch (Exception e) {
			log.error("StackTrace ", e);
		}
		return pd;
	}

}
