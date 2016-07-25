package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class PlayerDO extends AbstractDataObject {

	private Integer rowId;
	private String username;
	private String password;

	@Override
	public Integer getRowId() {
		return rowId;
	}

	@Override
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

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
		dataMap.put("username", "\""+getUsername()+"\"");
		dataMap.put("password", "\""+getPassword()+"\"");
		return dataMap;
	}
}
