package com.tigerit.bankaccount.model.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OperationType {
	CASH_PURCHASE(1l),
	INSTALLMENT_PURCHASE(2l),
	WITHDRAW(3l),
	PAYMENT(4l);

	@Getter
	private Long id;

}
