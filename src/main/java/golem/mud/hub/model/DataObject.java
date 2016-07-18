package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataObject {

	public abstract Integer getId();
	public abstract void setId(Integer id);

	public abstract String getTable();
	public abstract DataObject instance(ResultSet result) throws SQLException;
}
