package com.tigerit.bankaccount.controller;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tigerit.bankaccount.entity.AccountEntity;
import com.tigerit.bankaccount.model.dto.TransactionDTO;
import com.tigerit.bankaccount.repository.AccountRepository;
import com.tigerit.bankaccount.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigerit.bankaccount.Application;
import com.tigerit.bankaccount.entity.OperationTypeEntity;
import com.tigerit.bankaccount.model.enums.OperationType;
import com.tigerit.bankaccount.repository.OperationTypeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
		SpringBootTest.WebEnvironment.MOCK,
		classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-test.yml")
public class TransactionControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private OperationTypeRepository operationTypeRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	private static final String DOCUMENT_NUMBER = "111111111";

	private static final Long OPERATION_TYPE_ID = 1L;
	private static final Long OPERATION_TYPE_ID_FIVE = 5L;
	private static final Long ACCOUNT_ID = 1L;
	private static final Long ACCOUNT_ID_TWO = 2L;
	private static final BigDecimal AMOUNT = new BigDecimal( 100.25 );

	@Before
	public void setUp() {
		accountRepository.save( AccountEntity.builder()
				.documentNumber( DOCUMENT_NUMBER )
				.build() );

		createOperationType();
	}

	@Test
	public void shouldCreateTransaction() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/transactions")
						.content( new ObjectMapper().writeValueAsString(
								buildTransactionDTO(ACCOUNT_ID, OPERATION_TYPE_ID) ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ))
				.andExpect( MockMvcResultMatchers.status().isOk() );

		var result = transactionRepository.findByAccount(
				AccountEntity.builder()
						.id( 1L )
						.build());

		Assertions.assertThat( result.isPresent() ).isTrue();
		Assertions.assertThat( result.get().getAmount() ).isEqualTo( AMOUNT.negate() );
	}

	@Test
	public void shouldNotCreateTransactionWhenNotExistsAccount() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/transactions")
						.content( new ObjectMapper().writeValueAsString(
								buildTransactionDTO(ACCOUNT_ID_TWO, OPERATION_TYPE_ID) ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ))
				.andExpect( MockMvcResultMatchers.status().isBadRequest() );
	}

	@Test
	public void shouldNotCreateTransactionWhenNotExistsOperationType() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/transactions")
						.content( new ObjectMapper().writeValueAsString(
								buildTransactionDTO(ACCOUNT_ID, OPERATION_TYPE_ID_FIVE) ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ))
				.andExpect( MockMvcResultMatchers.status().isBadRequest() );
	}


	private void createOperationType() {
		Long id = 1l;
		for (OperationType operationType : OperationType.values()) {
			operationTypeRepository.save( buildOperationType( operationType, id++ ) );
		}
	}

	private OperationTypeEntity buildOperationType(OperationType operationType, Long id) {
		return OperationTypeEntity.builder()
				.id( id )
				.description( operationType.name() )
				.build();
	}

	private TransactionDTO buildTransactionDTO(Long accountId, Long operationTypeId) {
		return TransactionDTO.builder()
				.accountId( accountId )
				.operationTypeId( operationTypeId )
				.amount( AMOUNT )
				.build();
	}

}
