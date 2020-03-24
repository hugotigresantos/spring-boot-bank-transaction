package com.tigerit.bankaccount.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tigerit.bankaccount.model.dto.AccountDTO;
import com.tigerit.bankaccount.model.dto.TransactionDTO;

@RunWith(MockitoJUnitRunner.class)
public class BankFacadeTest {

	@InjectMocks
	private BankFacade bankFacade;

	@Mock
	private AccountService accountService;

	@Mock
	private TransactionService transactionService;

	private static final String DOCUMENT_NUMBER = "111111111";
	private static final Long ACCOUNT_ID = 1L;
	private static final Long OPERATION_TYPE_ID = 1L;

	@Test
	public void shouldSaveAccount() {
		bankFacade.saveAccount( DOCUMENT_NUMBER );

		Mockito.verify( accountService ).save( DOCUMENT_NUMBER );
	}

	@Test
	public void shouldGetAccountById() {
		Mockito.when( accountService.findById( ACCOUNT_ID ) )
				.thenReturn( buildAccountDTO() );

		var result = bankFacade.findAccountById( ACCOUNT_ID );

		Assertions.assertThat( result ).isNotNull();
		Assertions.assertThat( result.getAccountId() ).isEqualTo( ACCOUNT_ID );
		Assertions.assertThat( result.getDocumentNumber() ).isEqualTo( DOCUMENT_NUMBER );
	}

	@Test
	public void shouldSaveTransaction() {
		bankFacade.saveTransaction( buildTransactionDTO() );

		Mockito.verify( transactionService ).save( buildTransactionDTO() );
	}

	private AccountDTO buildAccountDTO() {
		return AccountDTO.builder()
				.accountId( ACCOUNT_ID )
				.documentNumber( DOCUMENT_NUMBER )
				.build();
	}

	private TransactionDTO buildTransactionDTO() {
		return TransactionDTO.builder()
				.accountId( ACCOUNT_ID )
				.operationTypeId( OPERATION_TYPE_ID )
				.build();
	}

}
