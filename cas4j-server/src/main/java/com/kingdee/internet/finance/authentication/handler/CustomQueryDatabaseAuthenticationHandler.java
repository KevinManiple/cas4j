package com.kingdee.internet.finance.authentication.handler;

import java.security.GeneralSecurityException;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public class CustomQueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

	@NotNull
	private UserNamePasswordEncoder userNamePasswordEncoder = new PlainTextUserNamePasswordEncoder();

	@NotNull
	private String sql;

	@Override
	protected HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential user)
			throws GeneralSecurityException, PreventedException {
		final String username = user.getUsername();
		final String encryptedPassword = this.getUserNamePasswordEncoder().encode(user.getUsername(),
				user.getPassword());
		final String encryptedOldPassword = this.getUserNamePasswordEncoder().encode(StringUtils.EMPTY,
				user.getPassword());
		try {
			final String dbPassword = getJdbcTemplate().queryForObject(this.sql, String.class, username);
			if (!dbPassword.equals(encryptedPassword) && !dbPassword.equals(encryptedOldPassword)) {
				throw new FailedLoginException("Password does not match value on record.");
			}
		} catch (final IncorrectResultSizeDataAccessException e) {
			if (e.getActualSize() == 0) {
				throw new AccountNotFoundException(username + " not found with SQL query");
			} else {
				throw new FailedLoginException("Multiple records found for " + username);
			}
		} catch (final DataAccessException e) {
			throw new PreventedException("SQL exception while executing query for " + username, e);
		}
		return createHandlerResult(user, this.principalFactory.createPrincipal(username), null);
	}

	public void setSql(final String sql) {
		this.sql = sql;
	}

	public UserNamePasswordEncoder getUserNamePasswordEncoder() {
		return userNamePasswordEncoder;
	}

	public void setUserNamePasswordEncoder(final UserNamePasswordEncoder userNamePasswordEncoder) {
		this.userNamePasswordEncoder = userNamePasswordEncoder;
	}

}
