package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Player extends AbstractDataObject {

	private Integer rowId;
	private String name;

	@Override
	public Integer getRowId() {
		return rowId;
	}

	@Override
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getTable() {
		return "player";
	}

	@Override
	public Player instance(ResultSet result) throws SQLException {
		Player instance = new Player();
		instance.setRowId(result.getInt("id"));
		instance.setName(result.getString("name"));
		return instance;
	}	
}
