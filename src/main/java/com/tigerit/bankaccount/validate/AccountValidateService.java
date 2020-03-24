package com.tigerit.bankaccount.validate;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.tigerit.bankaccount.exception.AccountAlreadyExistsException;
import com.tigerit.bankaccount.repository.AccountRepository;

@Component
@RequiredArgsConstructor
public class AccountValidateService {

	private final AccountRepository repository;

	public void validateSave(String documentNumber) {
		if (repository.findByDocumentNumber( documentNumber ).isPresent()) {
			throw new AccountAlreadyExistsException();
		}
	}

}
