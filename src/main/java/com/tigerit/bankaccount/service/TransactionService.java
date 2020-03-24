package com.tigerit.bankaccount.service;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.tigerit.bankaccount.entity.AccountEntity;
import com.tigerit.bankaccount.model.dto.TransactionDTO;
import com.tigerit.bankaccount.repository.TransactionRepository;
import com.tigerit.bankaccount.entity.OperationTypeEntity;
import com.tigerit.bankaccount.entity.TransactionEntity;
import com.tigerit.bankaccount.repository.OperationTypeRepository;
import com.tigerit.bankaccount.validate.TransactionValidateService;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final TransactionRepository repository;

	private final OperationTypeRepository operationTypeRepository;

	private final TransactionValidateService transactionValidateService;

	public void save(TransactionDTO transactionDTO) {

		transactionValidateService.validateSave( transactionDTO );

		repository.save( TransactionEntity.builder()
				.account( AccountEntity.builder()
					.id( transactionDTO.getAccountId() )
					.build())
				.operationType( OperationTypeEntity.builder()
					.id( transactionDTO.getOperationTypeId() )
					.build())
				.amount( getAmount( transactionDTO ) )
				.build()
			);
	}

	private BigDecimal getAmount(TransactionDTO transactionDTO) {
		OperationTypeEntity operationType = operationTypeRepository.findById( transactionDTO.getOperationTypeId() )
				.get();
		if (!operationType.isPayment()) {
			return transactionDTO.getAmount().negate();
		}
		return transactionDTO.getAmount();
	}

}
