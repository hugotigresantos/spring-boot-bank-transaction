package com.tigerit.bankaccount.exception.handler;

import lombok.RequiredArgsConstructor;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tigerit.bankaccount.exception.AccountAlreadyExistsException;
import com.tigerit.bankaccount.exception.BaseBankAccountException;
import com.tigerit.bankaccount.exception.AccountNotFoundException;
import com.tigerit.bankaccount.exception.OperationTypeNotFoundException;
import com.tigerit.bankaccount.util.MessageResourceUtil;

@RestControllerAdvice
@RequiredArgsConstructor
@Order(OrderedHandler.FIRST)
public class BankExceptionHandler {

	private final MessageResourceUtil messageResourceUtil;

	@ExceptionHandler(value = { AccountNotFoundException.class, AccountAlreadyExistsException.class,
			OperationTypeNotFoundException.class })
	public ResponseEntity<String> handleBaseException(BaseBankAccountException e) {
		String message = messageResourceUtil.getMessage( e.getMessage() );
		return ResponseEntity.badRequest().body( message );
	}
}
