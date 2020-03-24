package com.tigerit.bankaccount.util;

import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MessageResourceUtil {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String key) {
		return getMessage( key, null );
	}

	public String getMessage(String key, @Nullable Object[] params) {
		return messageSource.getMessage( key, params, Locale.getDefault() );
	}
}

