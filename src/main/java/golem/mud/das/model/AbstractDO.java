package golem.mud.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractDO {

    protected Integer rowId;

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    protected void addNull(final String name, final Map<String, String> map) {
        map.put(name, "null");
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

	public abstract String getTable();
	public abstract AbstractDO instance(ResultSet result) throws SQLException;
	public abstract Map<String, String> toMap();
}
