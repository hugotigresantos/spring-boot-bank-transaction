package com.tigerit.bankaccount.validate;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.tigerit.bankaccount.exception.AccountNotFoundException;
import com.tigerit.bankaccount.exception.OperationTypeNotFoundException;
import com.tigerit.bankaccount.model.dto.TransactionDTO;
import com.tigerit.bankaccount.repository.AccountRepository;
import com.tigerit.bankaccount.repository.OperationTypeRepository;

@Component
@RequiredArgsConstructor
public class TransactionValidateService {

	private final AccountRepository accountRepository;

	private final OperationTypeRepository operationTypeRepository;

	public void validateSave(TransactionDTO transactionDTO) {
		if (!accountRepository.existsById( transactionDTO.getAccountId() )) {
			throw new AccountNotFoundException();
		}

		if (!operationTypeRepository.existsById( transactionDTO.getOperationTypeId() )) {
			throw new OperationTypeNotFoundException();
		}
	}

}
