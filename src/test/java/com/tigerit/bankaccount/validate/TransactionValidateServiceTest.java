package com.tigerit.bankaccount.validate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tigerit.bankaccount.exception.AccountNotFoundException;
import com.tigerit.bankaccount.exception.OperationTypeNotFoundException;
import com.tigerit.bankaccount.model.dto.TransactionDTO;
import com.tigerit.bankaccount.repository.AccountRepository;
import com.tigerit.bankaccount.repository.OperationTypeRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionValidateServiceTest {

	@InjectMocks
	private TransactionValidateService service;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private OperationTypeRepository operationTypeRepository;

	private static final Long OPERATION_TYPE_ID = 1L;
	private static final Long ACCOUNT_ID = 1L;

	@Test
	public void shouldValidateSuccess() {
		Mockito.when( accountRepository.existsById( ArgumentMatchers.anyLong() ) )
				.thenReturn( true );
		Mockito.when( operationTypeRepository.existsById( ArgumentMatchers.anyLong() ) )
				.thenReturn( true );

		service.validateSave( buildTransactionDTO() );
	}

	@Test(expected = AccountNotFoundException.class)
	public void shouldValidateErrorWhenAccountNotFound() {
		service.validateSave( buildTransactionDTO() );
	}

	@Test(expected = OperationTypeNotFoundException.class)
	public void shouldValidateErrorWhenOperationNotFound() {
		Mockito.when( accountRepository.existsById( ArgumentMatchers.anyLong() ) )
				.thenReturn( true );

		service.validateSave( buildTransactionDTO() );
	}

	private TransactionDTO buildTransactionDTO() {
		return TransactionDTO.builder()
				.accountId( ACCOUNT_ID )
				.operationTypeId( OPERATION_TYPE_ID )
				.build();
	}

}
