package com.tigerit.bankaccount.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tigerit.bankaccount.model.dto.TransactionDTO;
import com.tigerit.bankaccount.service.BankFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/transactions", description = "Operations about transactions")
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

	private final BankFacade bankFacade;

	@ApiOperation(value = "Create a transaction")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Transaction created"),
			@ApiResponse(code = 400, message = "Validation errors"),
			@ApiResponse(code = 500, message = "Generic errors")})
	@PostMapping
	public ResponseEntity save(
			@ApiParam(value = "Transaction data")
			@RequestBody TransactionDTO transactionDTO) {
		bankFacade.saveTransaction( transactionDTO );
		return ResponseEntity.ok().build();
	}

}
