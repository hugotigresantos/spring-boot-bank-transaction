package com.tigerit.bankaccount.validate;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tigerit.bankaccount.entity.AccountEntity;
import com.tigerit.bankaccount.exception.AccountAlreadyExistsException;
import com.tigerit.bankaccount.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountValidateServiceTest {

	@InjectMocks
	private AccountValidateService service;

	@Mock
	private AccountRepository repository;

	private static final String DOCUMENT_NUMBER = "111111111";

	@Test
	public void shouldValidateSuccess() {
		service.validateSave( DOCUMENT_NUMBER );
	}

	@Test(expected = AccountAlreadyExistsException.class)
	public void shouldValidateErrorWhenAccountAlreadyExists() {
		Mockito.when( repository.findByDocumentNumber( ArgumentMatchers.anyString() ) )
				.thenReturn( Optional.of(
						AccountEntity.builder().build()
				) );

		service.validateSave( DOCUMENT_NUMBER );
	}

}
