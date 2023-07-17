package com.romu.inditex.service.dto;

import java.time.LocalDateTime;

public class RateToApplyInput {
	
	private LocalDateTime applicationDate;
	private Long productId;
	private String transactionIdentifier;
	
	public String toString() {
		return "RateToApplyInput [applicationDate=" + applicationDate + ", productId=" + productId
				+ ", transactionIdentifier=" + transactionIdentifier + "]";
	}
	
	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}
	public void setApplicattionDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getTransactionIdentifier() {
		return transactionIdentifier;
	}
	public void setTransactionIdentifier(String transactionIdentifier) {
		this.transactionIdentifier = transactionIdentifier;
	}
	
}
