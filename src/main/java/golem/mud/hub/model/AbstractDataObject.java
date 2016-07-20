package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDataObject {

	public abstract Integer getRowId();
	public abstract void setRowId(Integer rowId);

	public abstract String getTable();
	public abstract AbstractDataObject instance(ResultSet result) throws SQLException;
}
