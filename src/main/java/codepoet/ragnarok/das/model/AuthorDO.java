package codepoet.ragnarok.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AuthorDO extends AbstractDO {

	private String username;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getTable() {
		return "author";
	}

	@Override
	public AuthorDO instance(ResultSet result) throws SQLException {
		AuthorDO instance = new AuthorDO();
		instance.setRowId(result.getInt("id"));
		instance.setUsername(result.getString("username"));
		return instance;
	}

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("username", getUsername(), dataMap);
		return dataMap;
	}
}
