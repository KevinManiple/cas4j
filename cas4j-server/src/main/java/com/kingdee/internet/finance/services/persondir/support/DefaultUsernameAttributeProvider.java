package com.kingdee.internet.finance.services.persondir.support;

import java.util.List;
import java.util.Map;

import org.jasig.services.persondir.support.IUsernameAttributeProvider;

public class DefaultUsernameAttributeProvider implements IUsernameAttributeProvider {

	@Override
	public String getUsernameAttribute() {
		return null;
	}

	@Override
	public String getUsernameFromQuery(Map<String, List<Object>> query) {
		return null;
	}

}
