package com.bksoft.payment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bksoft.payment.model.PaymentDetails;

@RestController
public class PaymentController {

	private final static Log log = LogFactory.getLog(PaymentController.class);

	@RequestMapping(value = "paymentDetails")
	public PaymentDetails getPaymentDetails(@RequestParam(name = "eid", required = false) String enrollmentId) {

		PaymentDetails pd = null;
		try {
			pd = new PaymentDetails("1589", "10456", "12/12/2018", "145f45f45f");

		} catch (Exception e) {
			log.error("StackTrace ", e);
		}
		return pd;

	}
}
