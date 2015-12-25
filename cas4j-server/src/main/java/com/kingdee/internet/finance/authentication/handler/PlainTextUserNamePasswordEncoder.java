package com.kingdee.internet.finance.authentication.handler;

public final class PlainTextUserNamePasswordEncoder implements UserNamePasswordEncoder {

	@Override
	public String encode(final String username, final String password) {
		return username + password;
	}

}
