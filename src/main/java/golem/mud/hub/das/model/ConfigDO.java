package golem.mud.hub.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
//import java.util.HashMap;

public class ConfigDO extends AbstractDO {

	private Integer rowId;
    private Integer playerId;
    private Integer textSpeed;
    private String textColor;

	@Override
	public Integer getRowId() {
		return rowId;
	}

	@Override
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

    public Integer getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(final Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getTextSpeed() {
        return this.textSpeed;
    }

    public void setTextSpeed(final Integer textSpeed) {
        this.textSpeed = textSpeed;
    }

    public String getTextColor() {
        return this.textColor;
    }

    public void setTextColor(final String textColor) {
        this.textColor = textColor;
    }

	@Override
	public String getTable() {
		return "player";
	}

	@Override
	public ConfigDO instance(ResultSet result) throws SQLException {
        return null;
	}	

	@Override
	public Map<String, String> toMap() {
        return null;
	}
}
