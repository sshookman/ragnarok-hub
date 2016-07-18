package golem.mud.hub.das;

import java.util.Map;
import java.util.List;

import golem.mud.hub.model.DataObject;

public interface DataService {

	public List<DataObject> read(Map<String, Object> search);
	public DataObject read(Integer id);
	public Integer create(DataObject dataObject);
	public Integer update(DataObject dataObject);
	public Boolean delete(Integer id);

	public String getTable();
}
