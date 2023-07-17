package com.romu.inditex.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romu.inditex.controller.response.GetRateResponse;
import com.romu.inditex.exception.GetRatesExecutionException;
import com.romu.inditex.mapper.Mapper;
import com.romu.inditex.service.RatesService;
import com.romu.inditex.service.dto.RateToApplyOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rate to Apply", description = "Final price to apply")
@RestController
@RequestMapping("/")
public class RateToApplyController {
	Logger logger = LoggerFactory.getLogger(RateToApplyController.class);
	
	@Autowired
	private RatesService ratesService;
	
	@Operation(summary = "Get the final price of a given product")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = GetRateResponse.class), mediaType = "application/json") })
	@GetMapping("/get-rate")
	public ResponseEntity<GetRateResponse> getRate(String applicationDate, Long productId, @RequestParam(required = false) Optional<String> transactionIdentifier) {
		GetRateResponse response = new GetRateResponse();
		try {
			RateToApplyOutput rateToApplyOutput = ratesService.getRateToApply(Mapper.rateToApplyInputMapper(applicationDate, productId, transactionIdentifier));
			response = Mapper.rateToApplyOutputMapper(rateToApplyOutput);
			return new ResponseEntity<>(response, HttpStatus.OK); 
			
		} catch (GetRatesExecutionException e) {
			response.getErrors().add(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
