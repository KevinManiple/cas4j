package com.kingdee.internet.finance.authentication.handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DefaultUserNamePasswordEncoder implements UserNamePasswordEncoder {

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	@NotNull
	private String algorithm;

	private static final Logger logger = LoggerFactory.getLogger(DefaultUserNamePasswordEncoder.class);

	public DefaultUserNamePasswordEncoder(final String algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public String encode(final String userName, final String password) {
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update((password + userName).getBytes());
			final byte[] digest = messageDigest.digest();
			return getFormattedText(digest);
		} catch (final NoSuchAlgorithmException e) {
			logger.error("Username password encode error!", e);
			throw new SecurityException(e);
		}
	}

	private String getFormattedText(final byte[] bytes) {
		final StringBuilder buf = new StringBuilder(bytes.length * 2);

		for (int j = 0; j < bytes.length; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

}
