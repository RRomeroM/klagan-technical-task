package com.romu.inditex.service.dto;

import java.math.BigDecimal;

public class RateToApplyOutput {
	
	private Long productId;
	private String productName;
	private String chainIdentifier;
	private String rateToApply;
	private BigDecimal finalPrice;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getChainIdentifier() {
		return chainIdentifier;
	}
	public void setChainIdentifier(String chainIdentifier) {
		this.chainIdentifier = chainIdentifier;
	}
	public String getRateToApply() {
		return rateToApply;
	}
	public void setRateToApply(String rateToApply) {
		this.rateToApply = rateToApply;
	}
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	
}
