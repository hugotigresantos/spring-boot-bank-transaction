package com.tigerit.bankaccount.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.tigerit.bankaccount.model.dto.AccountDTO;
import com.tigerit.bankaccount.repository.AccountRepository;
import com.tigerit.bankaccount.entity.AccountEntity;
import com.tigerit.bankaccount.exception.AccountNotFoundException;
import com.tigerit.bankaccount.validate.AccountValidateService;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository repository;

	private final AccountValidateService accountValidateService;

	public void save(String documentNumber) {

		accountValidateService.validateSave( documentNumber );

		repository.save( AccountEntity.builder()
			.documentNumber( documentNumber )
			.build());
	}

	public AccountDTO findById(Long accountId) {
		var account = repository.findById( accountId )
				.orElseThrow( AccountNotFoundException::new );

		return AccountDTO.builder()
				.accountId( account.getId() )
				.documentNumber( account.getDocumentNumber() )
				.build();
	}

}
