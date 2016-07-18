package golem.mud.hub.das;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import golem.mud.hub.model.DataObject;

public abstract class SQLiteDataService<DO extends DataObject> {

	private DO dataObjectClass;
	private Connection story;

	public SQLiteDataService(final DO dataObject, final Connection story) {
		this.dataObjectClass = dataObject;
		this.story = story;
	}	
	
	public List<DO> read(Map<String, Object> search) {

		List<DO> dataObjects = new ArrayList<DO>();
		try {
			Statement statement = story.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM " + dataObjectClass.getTable());
			while (results.next()) {
				@SuppressWarnings("unchecked")
				DO dataObject = (DO) dataObjectClass.instance(results);
				dataObjects.add(dataObject);
			}
			results.close();
			statement.close();
		} catch (SQLException exception) {
			System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
			System.exit(0);
		}
		return dataObjects;
	}
	
	public DataObject read(final Integer id) {
		return null;
	}

	public Integer create(DataObject dataObject) {
		return 0;
	}

	public Integer update(final Integer id, final DataObject dataObject) {
		return 0;
	}

	public Boolean delete(final Integer id) {
		return false;
	}
}
