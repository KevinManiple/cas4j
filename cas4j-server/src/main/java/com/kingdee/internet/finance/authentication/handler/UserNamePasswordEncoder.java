package com.kingdee.internet.finance.authentication.handler;

public interface UserNamePasswordEncoder {

	String encode(String username, String password);

}
