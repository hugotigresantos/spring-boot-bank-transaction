package com.tigerit.bankaccount.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tigerit.bankaccount.entity.AccountEntity;
import com.tigerit.bankaccount.exception.AccountNotFoundException;
import com.tigerit.bankaccount.repository.AccountRepository;
import com.tigerit.bankaccount.validate.AccountValidateService;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	private static final String DOCUMENT_NUMBER = "111111111";
	private static final Long ACCOUNT_ID = 1L;

	@InjectMocks
	private AccountService service;

	@Mock
	private AccountRepository repository;

	@Mock
	private AccountValidateService accountValidateService;

	@Test
	public void shouldSave() {
		service.save( DOCUMENT_NUMBER );

		Mockito.verify( repository ).save( AccountEntity.builder()
				.documentNumber( DOCUMENT_NUMBER )
				.build() );
	}

	@Test
	public void shouldGetAccountByAccountId() {
		Mockito.when( repository.findById( ACCOUNT_ID ) )
				.thenReturn( Optional.of(
						AccountEntity.builder()
								.id( ACCOUNT_ID )
								.documentNumber( DOCUMENT_NUMBER )
								.build()
						)
				);

		var result = service.findById( ACCOUNT_ID );

		Assertions.assertThat( result ).isNotNull();
	}

	@Test(expected = AccountNotFoundException.class)
	public void shouldNotGetAccountWhenItDoesNotExists() {
		service.findById( ACCOUNT_ID );
	}

}
