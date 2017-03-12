package codepoet.ragnarok.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConfigDO extends AbstractDO {

	private Integer playerId;
	private Integer textSpeed;
	private String textColor;

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
		return "config";
	}

	@Override
	public ConfigDO instance(ResultSet result) throws SQLException {
		ConfigDO instance = new ConfigDO();
		instance.setRowId(result.getInt("id"));
		instance.setPlayerId(result.getInt("player_id"));
		instance.setTextSpeed(result.getInt("text_speed"));
		instance.setTextColor(result.getString("text_color"));
		return instance;
	}

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("player_id", getPlayerId(), dataMap);
		addNotNull("text_speed", getTextSpeed(), dataMap);
		addNotNull("text_color", getTextColor(), dataMap);
		return dataMap;
	}
}
