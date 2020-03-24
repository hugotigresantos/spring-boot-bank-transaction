package com.tigerit.bankaccount.exception;

public class AccountNotFoundException extends BaseBankAccountException {
	public AccountNotFoundException() {
		super("messages.account.not.found.exception");
	}
}
