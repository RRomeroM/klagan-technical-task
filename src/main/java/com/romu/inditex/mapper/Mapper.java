package com.romu.inditex.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import com.romu.inditex.controller.response.GetRateResponse;
import com.romu.inditex.service.dto.RateToApplyInput;
import com.romu.inditex.service.dto.RateToApplyOutput;

public class Mapper {

	public static RateToApplyInput rateToApplyInputMapper(String applicationDate, Long productId, Optional<String> transactionIdentifier) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

		try {
			RateToApplyInput input = new RateToApplyInput();
			input.setApplicattionDate(LocalDateTime.parse(applicationDate, formatter));
			input.setProductId(productId);
			if (transactionIdentifier.isPresent())
				input.setTransactionIdentifier(transactionIdentifier.get());
			else input.setTransactionIdentifier(UUID.randomUUID().toString());

			return input;

		} catch (Exception e) {
			throw e;
		}
	}

	public static GetRateResponse rateToApplyOutputMapper(RateToApplyOutput rateToApplyOutput) {
		GetRateResponse response = new GetRateResponse();
		response.setProductId(rateToApplyOutput.getProductId());
		response.setProductName(rateToApplyOutput.getProductName());
		response.setChainIdentifier(rateToApplyOutput.getChainIdentifier());
		response.setRateToApply(rateToApplyOutput.getRateToApply());
		response.setFinalPrice(rateToApplyOutput.getFinalPrice());

		return response;
	}

}
