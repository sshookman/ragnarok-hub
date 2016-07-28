package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class CommandDO extends AbstractDO {

	private Integer rowId;
	private Integer screenId;
	private Integer actionId;

	@Override
	public Integer getRowId() {
		return rowId;
	}

	@Override
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

    public Integer getScreenId() {
        return this.screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public Integer getActionId() {
        return this.actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

	@Override
	public String getTable() {
		return "command";
	}

	@Override
	public CommandDO instance(ResultSet result) throws SQLException {
		CommandDO instance = new CommandDO();
		instance.setRowId(result.getInt("id"));
		instance.setScreenId(result.getInt("screen_id"));
		instance.setActionId(result.getInt("action_id"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("screen_id", getScreenId(), dataMap);
		addNotNull("action_id", getActionId(), dataMap);
		return dataMap;
	}
}
