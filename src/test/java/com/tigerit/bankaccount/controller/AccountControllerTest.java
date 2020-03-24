package com.tigerit.bankaccount.controller;

import org.assertj.core.api.Assertions;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tigerit.bankaccount.repository.AccountRepository;
import com.tigerit.bankaccount.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
		SpringBootTest.WebEnvironment.MOCK,
		classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-test.yml")
public class AccountControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AccountRepository repository;

	private static final String DOCUMENT_NUMBER = "111111111";
	private static final String DOCUMENT_NUMBER_TWO = "111111112";

	@Test
	public void shouldCreateAndFind() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/accounts")
						.content( DOCUMENT_NUMBER )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ))
				.andExpect( MockMvcResultMatchers.status().isOk() );

		mvc.perform(
				MockMvcRequestBuilders.get("/accounts/{accountId}", 1)
						.accept( MediaType.APPLICATION_JSON ))
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andExpect( MockMvcResultMatchers.jsonPath( "$.document_number" ).value( DOCUMENT_NUMBER ) );

		var result = repository.findById( 1l );
		Assertions.assertThat( result.isPresent() ).isTrue();
		Assertions.assertThat( result.get().getDocumentNumber() ).isEqualTo( DOCUMENT_NUMBER );
	}

	@Test
	public void shouldNotCreateWhenAccountAlreadyExists() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/accounts")
						.content( DOCUMENT_NUMBER_TWO )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ))
				.andExpect( MockMvcResultMatchers.status().isOk() );

		mvc.perform(
				MockMvcRequestBuilders.post("/accounts")
						.content( DOCUMENT_NUMBER_TWO )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ))
				.andDo( MockMvcResultHandlers.print(  ) )
				.andExpect( MockMvcResultMatchers.status().isBadRequest() );

	}

	@Test
	public void shouldNotFindAccountWhenNotExists() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/accounts/{accountId}", 100)
						.accept( MediaType.APPLICATION_JSON ))
				.andExpect( MockMvcResultMatchers.status().isBadRequest() );
	}

}
