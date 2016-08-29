package golem.mud.hub.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

import golem.mud.das.model.AbstractDO;

public class StoryDO extends AbstractDO {

	private Integer rowId;
	private String name;
	private String path;

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

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getTable() {
		return "story";
	}

	@Override
	public StoryDO instance(ResultSet result) throws SQLException {
		StoryDO instance = new StoryDO();
		instance.setRowId(result.getInt("id"));
		instance.setName(result.getString("name"));
		instance.setPath(result.getString("path"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("name", getName(), dataMap);
		addNotNull("path", getPath(), dataMap);
		return dataMap;
	}
}
