package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Player extends AbstractDataObject {

	private Integer id;
	private String name;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
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
		instance.setId(result.getInt("id"));
		instance.setName(result.getString("name"));
		return instance;
	}	
}
