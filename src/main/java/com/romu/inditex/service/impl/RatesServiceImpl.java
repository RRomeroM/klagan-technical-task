package com.romu.inditex.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romu.inditex.entity.Price;
import com.romu.inditex.entity.Product;
import com.romu.inditex.exception.GetRatesExecutionException;
import com.romu.inditex.repository.PriceRepository;
import com.romu.inditex.repository.ProductRepository;
import com.romu.inditex.service.RatesService;
import com.romu.inditex.service.dto.RateToApplyInput;
import com.romu.inditex.service.dto.RateToApplyOutput;

@Service
public class RatesServiceImpl implements RatesService {
	Logger logger = LoggerFactory.getLogger(RatesServiceImpl.class);
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public RateToApplyOutput getRateToApply(RateToApplyInput input) throws GetRatesExecutionException {
		logger.info(input.toString());
		RateToApplyOutput output = new RateToApplyOutput();
		try {
			List<Price> priceList = priceRepository.getFinalPriceToApply(input.getApplicationDate(), input.getProductId());
			if (priceList.size() > 0) {
				Price productPricingDetail = priceList.get(0);
				output = new RateToApplyOutput();
				output.setProductId(productPricingDetail.getProduct().getProductId());
				output.setProductName(productPricingDetail.getProduct().getProductName());
				output.setRateToApply(generateRateToApplyDescription(productPricingDetail));
				output.setFinalPrice(productPricingDetail.getPrice());
				output.setChainIdentifier(input.getTransactionIdentifier());

			} else {
				Optional<Product> product = productRepository.findById(input.getProductId());
				if (product.isPresent()) {
					output.setProductId(product.get().getProductId());
					output.setProductName(product.get().getProductName());
					output.setRateToApply("LIST PRICE");
					output.setFinalPrice(product.get().getListPrice());
					output.setChainIdentifier(input.getTransactionIdentifier());

				} else {
					output = new RateToApplyOutput();
					output.setProductId(0L);
					output.setProductName("PRODUCT NOT FOUND");
					output.setRateToApply("N/A");
					output.setFinalPrice(BigDecimal.ZERO);
					output.setChainIdentifier(input.getTransactionIdentifier());
				}
			}
		} catch (Exception e) {
			String exceptionMessage = String.format("UNABLE to find product due to an UNEXPECTED EXCEPTION. InputData: %s", input.toString());
			logger.error(exceptionMessage, e);
			throw new GetRatesExecutionException(e.getMessage());
		}
		
		logger.info(output.toString());
		return output;
	}

	private String generateRateToApplyDescription(Price productPricingDetail) {
		StringBuilder sb = new StringBuilder();
		sb.append("Price List: ").append(productPricingDetail.getPriceList()).append(" | ");
		sb.append("Start Date: ").append(productPricingDetail.getStartDate()).append(" | ");
		sb.append("End Date: ").append(productPricingDetail.getEndDate()).append(" | ");
		sb.append("Priority: ").append(productPricingDetail.getPriority()).append(" | ");
		sb.append("Final Price: ").append(productPricingDetail.getPrice());
		return sb.toString();
	}

}
