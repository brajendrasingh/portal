package com.bksoft.enrollment.model;

public class PaymentDetails {

	private String paymentAmount;
	private String totalAmount;
	private String paymentDate;
	private String paymentId;

	public PaymentDetails(String paymentAmount, String totalAmount, String paymentDate, String paymentId) {
		this.paymentAmount = paymentAmount;
		this.totalAmount = totalAmount;
		this.paymentDate = paymentDate;
		this.paymentId = paymentId;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	@Override
	public String toString() {
		return "PaymentDetails [paymentAmount=" + paymentAmount + ", totalAmount=" + totalAmount + ", paymentDate="
				+ paymentDate + ", paymentId=" + paymentId + "]";
	}

}
