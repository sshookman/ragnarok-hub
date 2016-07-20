package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Chapter extends AbstractDataObject {

	private Integer rowId;
	private Integer prevChapterRowId;
	private String title;
	private String body;

	@Override
	public Integer getRowId() {
		return rowId;
	}

	@Override
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	public Integer getPrevChapterRowId() {
		return prevChapterRowId;
	}

	public void setPrevChapterRowId(Integer prevChapterRowId) {
		this.prevChapterRowId = prevChapterRowId;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String getTable() {
		return "chapter";
	}

	@Override
	public Chapter instance(ResultSet result) throws SQLException {
		Chapter instance = new Chapter();
		instance.setRowId(result.getInt("id"));
		instance.setPrevChapterRowId(result.getInt("prev_chapter_id"));
		instance.setTitle(result.getString("title"));
		instance.setBody(result.getString("body"));
		return instance;
	}
}
