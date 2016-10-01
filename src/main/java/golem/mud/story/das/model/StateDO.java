package golem.mud.story.das.model;

import golem.mud.common.das.model.AbstractDO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StateDO extends AbstractDO {

    private Integer locationEntityId;

    public Integer getLocationEntityId() {
        return locationEntityId;
    }

    public void setLocationEntityId(Integer locationEntityId) {
        this.locationEntityId = locationEntityId;
    }

    @Override
    public String getTable() {
        return "state";
    }

    @Override
    public StateDO instance(ResultSet result) throws SQLException {
        StateDO instance = new StateDO();
        instance.setRowId(result.getInt("id"));
        instance.setLocationEntityId(result.getInt("location_entity_id"));
        return instance;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> dataMap = new HashMap<>();
        addNotNull("location_entity_id", locationEntityId, dataMap);
        return dataMap;
    }
}
