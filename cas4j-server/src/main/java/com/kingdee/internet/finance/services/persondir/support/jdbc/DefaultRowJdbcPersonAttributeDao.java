package com.kingdee.internet.finance.services.persondir.support.jdbc;

import java.util.List;
import java.util.Map;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.jdbc.AbstractJdbcPersonAttributeDao;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

@SuppressWarnings("deprecation")
public class DefaultRowJdbcPersonAttributeDao extends AbstractJdbcPersonAttributeDao<Map<String, Object>> {

	@Override
	protected List<IPersonAttributes> parseAttributeMapFromResults(List<Map<String, Object>> queryResults,
			String queryUserName) {
		return null;
	}

	@Override
	protected ParameterizedRowMapper<Map<String, Object>> getRowMapper() {
		return null;
	}

}
