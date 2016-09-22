package golem.mud.story.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class EntryComponentDO extends AbstractComponentDO {

    private Integer position;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

	@Override
	public String getTable() {
		return "c_entry";
	}

	@Override
	public EntryComponentDO instance(ResultSet result) throws SQLException {
		EntryComponentDO instance = new EntryComponentDO();
		instance.setRowId(result.getInt("id"));
		instance.setEntityId(result.getInt("entity_id"));
		instance.setPosition(result.getInt("position"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("entity_id", entityId, dataMap);
		addNotNull("position", position, dataMap);
		return dataMap;
	}
}
