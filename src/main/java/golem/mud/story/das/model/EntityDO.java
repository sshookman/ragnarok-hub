package golem.mud.story.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

import golem.mud.common.das.model.AbstractDO;

public class EntityDO extends AbstractDO {

	@Override
	public String getTable() {
		return "entity";
	}

	@Override
	public EntityDO instance(ResultSet result) throws SQLException {
		EntityDO instance = new EntityDO();
		instance.setRowId(result.getInt("id"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNull("id", dataMap);
		return dataMap;
	}
}
