package codepoet.ragnarok.das.model;

import codepoet.vaultmonkey.annotations.SqliteColumn;
import codepoet.vaultmonkey.annotations.SqliteObject;

@SqliteObject(table = "config")
public class ConfigDO {

	@SqliteColumn
	private Integer id;
	@SqliteColumn
	private Integer playerId;
	@SqliteColumn
	private Integer textSpeed;
	@SqliteColumn
	private String textColor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
}
