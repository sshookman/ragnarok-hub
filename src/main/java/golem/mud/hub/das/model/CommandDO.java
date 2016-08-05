package golem.mud.hub.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class CommandDO extends AbstractDO {

	private Integer rowId;
	private String name;
	private String targets;

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

    public String getTargets() {
        return this.targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

	@Override
	public String getTable() {
		return "command";
	}

	@Override
	public CommandDO instance(ResultSet result) throws SQLException {
		CommandDO instance = new CommandDO();
		instance.setRowId(result.getInt("id"));
		instance.setName(result.getString("name"));
		instance.setTargets(result.getString("targets"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("name", getName(), dataMap);
		addNotNull("targets", getTargets(), dataMap);
		return dataMap;
	}
}
