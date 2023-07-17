package com.romu.inditex.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.romu.inditex.entity.Price;

public interface PriceRepository extends CrudRepository<Price, Long>{
	
	@Query("SELECT p FROM Price p WHERE startDate <= ?1 AND endDate > ?1 AND product.productId = ?2 ORDER BY priority DESC")
	public List<Price> getFinalPriceToApply(LocalDateTime applicationDate, Long productId);
	
}
