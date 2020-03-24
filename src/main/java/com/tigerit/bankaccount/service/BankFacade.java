package com.tigerit.bankaccount.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.tigerit.bankaccount.model.dto.AccountDTO;
import com.tigerit.bankaccount.model.dto.TransactionDTO;

@Service
@RequiredArgsConstructor
public class BankFacade {

	private final AccountService accountService;

	private final TransactionService transactionService;

	public void saveAccount(String documentNumber) {
		accountService.save( documentNumber );
	}

	public AccountDTO findAccountById(Long accountId) {
		return accountService.findById( accountId );
	}

	public void saveTransaction(TransactionDTO transactionDTO) {
		transactionService.save( transactionDTO );
	}



}
