package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractDataObject {

	public abstract Integer getRowId();
	public abstract void setRowId(Integer rowId);

	public abstract String getTable();
	public abstract AbstractDataObject instance(ResultSet result) throws SQLException;

	public abstract Map<String, String> toMap();
}
