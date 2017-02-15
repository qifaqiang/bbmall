package com.wxsoft.framework.bean;

import java.util.ArrayList;
import java.util.List;

public class SqlExecuteBean {
	private String sql;

	private List<Object[]> params;

	public SqlExecuteBean(String sql, int length) {
		this.sql = sql;
		params = new ArrayList<Object[]>(length);
	}

	public void addParams(Object[] oneParam) {
		params.add(oneParam);
	}

	public List<Object[]> getParams() {
		return params;
	}

	public int getParamsLength() {
		return params == null ? 0 : params.size();
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
