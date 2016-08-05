package golem.mud.hub.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class SavedDO extends AbstractDO {

	private Integer rowId;
	private String name;
	private String path;
    private Integer playerId;
    private Integer golemId;

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

	public Integer getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getGolemId() {
		return this.golemId;
	}

	public void setGolemId(Integer golemId) {
		this.golemId = golemId;
	}

	@Override
	public String getTable() {
		return "saved";
	}

	@Override
	public SavedDO instance(ResultSet result) throws SQLException {
		SavedDO instance = new SavedDO();
		instance.setRowId(result.getInt("id"));
		instance.setName(result.getString("name"));
		instance.setPath(result.getString("path"));
		instance.setPlayerId(result.getInt("player_id"));
		instance.setGolemId(result.getInt("golem_id"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("name", getName(), dataMap);
		addNotNull("path", getPath(), dataMap);
		addNotNull("player_id", getPlayerId(), dataMap);
		addNotNull("golem_id", getGolemId(), dataMap);
		return dataMap;
	}
}
