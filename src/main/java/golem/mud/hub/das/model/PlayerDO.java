package golem.mud.hub.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

import golem.mud.common.das.model.AbstractDO;

public class PlayerDO extends AbstractDO {

	private String username;
	private String password;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getTable() {
		return "player";
	}

	@Override
	public PlayerDO instance(ResultSet result) throws SQLException {
		PlayerDO instance = new PlayerDO();
		instance.setRowId(result.getInt("id"));
		instance.setUsername(result.getString("username"));
		instance.setPassword(result.getString("password"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("username", getUsername(), dataMap);
		addNotNull("password", getPassword(), dataMap);
		return dataMap;
	}
}
