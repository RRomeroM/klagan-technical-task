package com.romu.inditex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.romu.inditex.service.RatesService;
import com.romu.inditex.service.dto.RateToApplyInput;
import com.romu.inditex.service.dto.RateToApplyOutput;

@SpringBootTest
class RatesServiceTest {
	
	@Autowired
	private RatesService ratesService;
	
	@Test
	@DisplayName("Test N°1 : Request at 10:00 am on the 14th for product 35455, final price should be 35.50")
	void exampleDataTest1() {
		RateToApplyInput input = new RateToApplyInput();
		input.setApplicattionDate(LocalDateTime.parse("2020-06-14T10:00:00.000"));
		input.setProductId(35455L);
		input.setTransactionIdentifier(UUID.randomUUID().toString());
		try {
			RateToApplyOutput output = ratesService.getRateToApply(input);
			assertThat(output.getFinalPrice()).isEqualByComparingTo(BigDecimal.valueOf(35.50));
		} catch (GetRatesExecutionException e) {
			fail(String.format("Unexpected exception thrown: %s", e.getMessage()));
		}
	}
	
	@Test
	@DisplayName("Test N°2 : Request at 04:00 pm on the 14th for product 35455, final price should be 25.45")
	void exampleDataTest2() {
		RateToApplyInput input = new RateToApplyInput();
		input.setApplicattionDate(LocalDateTime.parse("2020-06-14T16:00:00.000"));
		input.setProductId(35455L);
		input.setTransactionIdentifier(UUID.randomUUID().toString());
		try {
			RateToApplyOutput output = ratesService.getRateToApply(input);
			assertThat(output.getFinalPrice()).isEqualByComparingTo(BigDecimal.valueOf(25.45));
		} catch (GetRatesExecutionException e) {
			fail(String.format("Unexpected exception thrown: %s", e.getMessage()));
		}
	}
	
	@Test
	@DisplayName("Test N°3 : Request at 09:00 pm on the 14th for product 35455, final price should be 35.50")
	void exampleDataTest3() {
		RateToApplyInput input = new RateToApplyInput();
		input.setApplicattionDate(LocalDateTime.parse("2020-06-14T21:00:00.000"));
		input.setProductId(35455L);
		input.setTransactionIdentifier(UUID.randomUUID().toString());
		try {
			RateToApplyOutput output = ratesService.getRateToApply(input);
			assertThat(output.getFinalPrice()).isEqualByComparingTo(BigDecimal.valueOf(35.50));
		} catch (GetRatesExecutionException e) {
			fail(String.format("Unexpected exception thrown: %s", e.getMessage()));
		}
	}
	
	@Test
	@DisplayName("Test N°4 : Request at 10:00 am on the 15th for product 35455, final price should be 30.50")
	void exampleDataTest4() {
		RateToApplyInput input = new RateToApplyInput();
		input.setApplicattionDate(LocalDateTime.parse("2020-06-15T10:00:00.000"));
		input.setProductId(35455L);
		input.setTransactionIdentifier(UUID.randomUUID().toString());
		try {
			RateToApplyOutput output = ratesService.getRateToApply(input);
			assertThat(output.getFinalPrice()).isEqualByComparingTo(BigDecimal.valueOf(30.50));
		} catch (GetRatesExecutionException e) {
			fail(String.format("Unexpected exception thrown: %s", e.getMessage()));
		}
	}
	
	@Test
	@DisplayName("Test N°5 : Request at 09:00 pm on the 16th for product 35455, final price should be 38.95")
	void exampleDataTest5() {
		RateToApplyInput input = new RateToApplyInput();
		input.setApplicattionDate(LocalDateTime.parse("2020-06-16T21:00:00.000"));
		input.setProductId(35455L);
		input.setTransactionIdentifier(UUID.randomUUID().toString());
		try {
			RateToApplyOutput output = ratesService.getRateToApply(input);
			assertThat(output.getFinalPrice()).isEqualByComparingTo(BigDecimal.valueOf(38.95));
		} catch (GetRatesExecutionException e) {
			fail(String.format("Unexpected exception thrown: %s", e.getMessage()));
		}
	}
	
	@Test
	@DisplayName("Test N°6 : Request a product on a nonexistent date/product in the Prices Table, final price should be the list price: 115.00")
	void productNotItPriceList() {
		RateToApplyInput input = new RateToApplyInput();
		input.setApplicattionDate(LocalDateTime.parse("2022-06-16T21:00:00.000"));
		input.setProductId(35455L);
		input.setTransactionIdentifier(UUID.randomUUID().toString());
		try {
			RateToApplyOutput output = ratesService.getRateToApply(input);
			assertThat(output.getFinalPrice()).isEqualByComparingTo(BigDecimal.valueOf(115.00));
		} catch (GetRatesExecutionException e) {
			fail(String.format("Unexpected exception thrown: %s", e.getMessage()));
		}
	}
	
	@Test
	@DisplayName("Test N°7 : Request a nonexistent product, productId equals to 0")
	void nonExistantProduct() {
		RateToApplyInput input = new RateToApplyInput();
		input.setApplicattionDate(LocalDateTime.parse("2023-07-17T00:00:00.000"));
		input.setProductId(999L);
		input.setTransactionIdentifier(UUID.randomUUID().toString());
		try {
			RateToApplyOutput output = ratesService.getRateToApply(input);
			assertEquals(0L, output.getProductId());
		} catch (GetRatesExecutionException e) {
			fail(String.format("Unexpected exception thrown: %s", e.getMessage()));
		}
	}

}
