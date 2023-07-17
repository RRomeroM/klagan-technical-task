package com.romu.inditex.service;

import com.romu.inditex.GetRatesExecutionException;
import com.romu.inditex.service.dto.RateToApplyInput;
import com.romu.inditex.service.dto.RateToApplyOutput;

public interface RatesService {
	
	public RateToApplyOutput getRateToApply(RateToApplyInput input) throws GetRatesExecutionException;

}
