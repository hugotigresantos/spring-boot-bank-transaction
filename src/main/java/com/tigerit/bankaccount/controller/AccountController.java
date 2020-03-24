package com.tigerit.bankaccount.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tigerit.bankaccount.model.dto.AccountDTO;
import com.tigerit.bankaccount.service.BankFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/accounts", description = "Operations about accounts")
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

	private final BankFacade bankFacade;

	@ApiOperation(value = "Create an account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Account created"),
			@ApiResponse(code = 400, message = "Validation errors"),
			@ApiResponse(code = 500, message = "Generic errors")})
	@PostMapping
	public ResponseEntity save(
			@ApiParam(value = "Document number")
			@RequestBody String documentNumber) {
		bankFacade.saveAccount( documentNumber );
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Find information of an account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Account found", response = AccountDTO.class),
			@ApiResponse(code = 400, message = "Validation errors"),
			@ApiResponse(code = 500, message = "Generic errors")})
	@GetMapping("/{accountId}")
	public ResponseEntity<AccountDTO> findById(
			@ApiParam(value = "Account Identifier")
			@PathVariable("accountId") Long accountId) {
		return ResponseEntity.ok( bankFacade.findAccountById( accountId ) );
	}


}
