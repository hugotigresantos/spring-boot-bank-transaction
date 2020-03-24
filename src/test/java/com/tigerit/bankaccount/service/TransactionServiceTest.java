package com.tigerit.bankaccount.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tigerit.bankaccount.entity.AccountEntity;
import com.tigerit.bankaccount.model.dto.TransactionDTO;
import com.tigerit.bankaccount.repository.TransactionRepository;
import com.tigerit.bankaccount.entity.OperationTypeEntity;
import com.tigerit.bankaccount.entity.TransactionEntity;
import com.tigerit.bankaccount.model.enums.OperationType;
import com.tigerit.bankaccount.repository.OperationTypeRepository;
import com.tigerit.bankaccount.validate.TransactionValidateService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	@InjectMocks
	private TransactionService service;

	@Mock
	private TransactionRepository repository;

	@Mock
	private TransactionValidateService transactionValidateService;

	@Mock
	private OperationTypeRepository operationTypeRepository;

	@Captor
	private ArgumentCaptor<TransactionEntity> transactionCaptor;

	private static final OperationType OPERATION_TYPE_DEBIT = OperationType.CASH_PURCHASE;
	private static final OperationType OPERATION_TYPE_CREDIT = OperationType.PAYMENT;
	private static final Long ACCOUNT_ID = 1L;
	private static final BigDecimal AMOUNT = new BigDecimal( 100.25 );

	@Test
	public void shouldSaveWithDebitOperation() {
		checkTransaction( OPERATION_TYPE_DEBIT );
	}

	@Test
	public void shouldSaveWithCreditOperation() {
		checkTransaction( OPERATION_TYPE_CREDIT );
	}


	private void checkTransaction(OperationType operationType) {
		Mockito.when( operationTypeRepository.findById( operationType.getId() ) )
				.thenReturn( Optional.of( buildOperationType(  operationType) ) );

		service.save( buildTransactionDTO(operationType.getId()) );

		Mockito.verify( repository ).save( transactionCaptor.capture() );
		var transactionSaved = transactionCaptor.getValue();
		var transactionExpected = buildTransactionEntity(operationType.getId());
		Assertions.assertThat( transactionSaved.getOperationType() ).isEqualTo( transactionExpected.getOperationType() );
		Assertions.assertThat( transactionSaved.getAmount() ).isEqualTo( transactionExpected.getAmount() );
		Assertions.assertThat( transactionSaved.getAccount() ).isEqualTo( transactionExpected.getAccount() );
	}

	private TransactionEntity buildTransactionEntity(Long operationTypeId) {
		return TransactionEntity.builder()
				.account( AccountEntity.builder()
						.id( ACCOUNT_ID )
						.build() )
				.operationType( OperationTypeEntity.builder()
						.id( operationTypeId )
						.build() )
				.amount( operationTypeId == 4 ? AMOUNT : AMOUNT.negate() )
				.build();
	}

	private TransactionDTO buildTransactionDTO(Long operationTypeId) {
		return TransactionDTO.builder()
				.accountId( ACCOUNT_ID )
				.operationTypeId( operationTypeId )
				.amount( AMOUNT )
				.build();
	}

	private OperationTypeEntity buildOperationType(OperationType operationType) {
		return OperationTypeEntity.builder()
				.id( operationType.getId() )
				.description( operationType.name() )
				.build();
	}

}
