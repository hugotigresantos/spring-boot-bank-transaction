package com.tigerit.bankaccount.exception;

public class AccountAlreadyExistsException extends BaseBankAccountException {

	public AccountAlreadyExistsException() {
		super("messages.account.already.exists.exception");
	}

}
