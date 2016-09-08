package golem.mud.story.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class PickupComponentDO extends AbstractComponentDO {

    private Boolean stackable;
    private Integer quantity;

    public boolean isStackable() {
        return this.stackable;
    }

    public void setStackable(Boolean stackable) {
        this.stackable = stackable;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	@Override
	public String getTable() {
		return "c_pickup";
	}

	@Override
	public PickupComponentDO instance(ResultSet result) throws SQLException {
		PickupComponentDO instance = new PickupComponentDO();
		instance.setRowId(result.getInt("id"));
		instance.setEntityId(result.getInt("entity_id"));
		instance.setStackable(result.getBoolean("stackable"));
		instance.setQuantity(result.getInt("quantity"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("entity_id", entityId, dataMap);
		addNotNull("stackable", stackable, dataMap);
		addNotNull("quantity", quantity, dataMap);
		return dataMap;
	}
}
