package com.tigerit.bankaccount.exception.handler;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tigerit.bankaccount.util.MessageResourceUtil;

@RestControllerAdvice
@RequiredArgsConstructor
@Order(OrderedHandler.LAST)
public class UnexpectedErrorHandler {

	private final MessageResourceUtil messageResourceUtil;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<List<String>> handleException(Exception e) {
		return ResponseEntity
				.status( HttpStatus.INTERNAL_SERVER_ERROR )
				.body( Arrays.asList( messageResourceUtil.getMessage( "messages.unexpected.error" ) ) );
	}


}
