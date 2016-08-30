package golem.mud.hub.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

import golem.mud.das.model.AbstractDO;

public class ActionDO extends AbstractDO {

    private String type;
    private Integer screenId;
    private Integer golemId;
    private String property;
    private String value;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getScreenId() {
        return this.screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public Integer getGolemId() {
        return this.golemId;
    }

    public void setGolemId(Integer golemId) {
        this.golemId = golemId;
    }

    public String getProperty() {
        return this.property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	@Override
	public String getTable() {
		return "action";
	}

	@Override
	public ActionDO instance(ResultSet result) throws SQLException {
		ActionDO instance = new ActionDO();
		instance.setRowId(result.getInt("id"));
		instance.setType(result.getString("type"));
		instance.setScreenId(result.getInt("screen_id"));
		instance.setGolemId(result.getInt("golem_id"));
		instance.setProperty(result.getString("property"));
		instance.setValue(result.getString("value"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("type", getType(), dataMap);
		addNotNull("screen_id", getScreenId(), dataMap);
		addNotNull("golem_id", getGolemId(), dataMap);
		addNotNull("property", getProperty(), dataMap);
		addNotNull("value", getValue(), dataMap);
		return dataMap;
	}
}
