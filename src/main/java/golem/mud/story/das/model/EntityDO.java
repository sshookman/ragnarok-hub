package golem.mud.story.das.model;

import golem.mud.common.das.model.AbstractDO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EntityDO extends AbstractDO {

    @Override
    public String getTable() {
        return "entity";
    }

    @Override
    public EntityDO instance(ResultSet result) throws SQLException {
        EntityDO instance = new EntityDO();
        instance.setRowId(result.getInt("id"));
        return instance;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> dataMap = new HashMap<>();
        addNull("id", dataMap);
        return dataMap;
    }
}
