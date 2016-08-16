package golem.mud.hub.das.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractDO {

    private static final ObjectMapper MAPPER = new ObjectMapper();
	public abstract Integer getRowId();
	public abstract void setRowId(Integer rowId);

	public abstract String getTable();
	public abstract AbstractDO instance(ResultSet result) throws SQLException;

	public abstract Map<String, String> toMap();

    public Map<String, String> getMap() {
        return MAPPER.convertValue(this, Map.class);
    }

    public AbstractDO getObject(final Map<String, Object> dataMap) {
        return MAPPER.convertValue(dataMap, AbstractDO.class);
    }

    protected void addNotNull(final String name, final String value, final Map<String, String> map) {
		if (value != null && !value.isEmpty()) {
			map.put(name, "\""+value+"\"");
		}
	}

    protected void addNotNull(final String name, final Integer value, final Map<String, String> map) {
		if (value != null) {
			map.put(name, value.toString());
		}
	}
}
