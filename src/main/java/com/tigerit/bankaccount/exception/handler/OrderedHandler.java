package com.tigerit.bankaccount.exception.handler;

import lombok.experimental.UtilityClass;

import org.springframework.core.Ordered;

@UtilityClass
public class OrderedHandler {
	public static final int FIRST = 1;
	public static final int LAST = Ordered.LOWEST_PRECEDENCE;
}
