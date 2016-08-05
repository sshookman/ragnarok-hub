package golem.mud.hub.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class ScreenDO extends AbstractDO {
    
    private Integer rowId;
    private String message;

    @Override
    public Integer getRowId() {
        return this.rowId;
    }

    @Override
    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getTable() {
        return "screen";
    }

    @Override
    public ScreenDO instance(ResultSet result) throws SQLException {
        ScreenDO instance = new ScreenDO();
        instance.setRowId(result.getInt("id"));
        instance.setMessage(result.getString("message"));
        return instance;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> dataMap = new HashMap<String, String>();
        addNotNull("message", getMessage(), dataMap);
        return dataMap;
    }
}
