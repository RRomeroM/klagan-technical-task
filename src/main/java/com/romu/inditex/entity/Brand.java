package com.romu.inditex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Brand {
	
	@Id
	private Long brandId;
	private String brandName;
	
	public String toString() {
		return "Brand [brandId=" + brandId + ", brandName=" + brandName + "]";
	}
	
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
}
