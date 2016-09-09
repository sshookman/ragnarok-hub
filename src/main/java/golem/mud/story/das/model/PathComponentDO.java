package golem.mud.story.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class PathComponentDO extends AbstractComponentDO {

    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String getTable() {
		return "c_path";
	}

	@Override
	public PathComponentDO instance(ResultSet result) throws SQLException {
		PathComponentDO instance = new PathComponentDO();
		instance.setRowId(result.getInt("id"));
		instance.setEntityId(result.getInt("entity_id"));
		instance.setName(result.getString("name"));
		instance.setMessage(result.getString("message"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("entity_id", entityId, dataMap);
		addNotNull("name", name, dataMap);
		addNotNull("message", message, dataMap);
		return dataMap;
	}
}
