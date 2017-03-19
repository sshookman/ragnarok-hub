package codepoet.ragnarok.das.model;

import codepoet.vaultmonkey.annotations.SqliteColumn;
import codepoet.vaultmonkey.annotations.SqliteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@SqliteObject(table = "story")
public class StoryDO extends AbstractDO {

	@SqliteColumn
	private Integer id;
	@SqliteColumn
	private String name;
	@SqliteColumn
	private String path;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
