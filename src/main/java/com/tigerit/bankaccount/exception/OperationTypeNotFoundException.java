package com.tigerit.bankaccount.exception;

public class OperationTypeNotFoundException extends BaseBankAccountException {
	public OperationTypeNotFoundException() {
		super("messages.operation.type.not.found.exception");
	}
}
