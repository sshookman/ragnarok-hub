package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Chapter extends DataObject {

	private Integer id;
	private Integer prevChapterId;
	private String title;
	private String body;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPrevChapterId() {
		return prevChapterId;
	}

	public void setPrevChapterId(Integer prevChapterId) {
		this.prevChapterId = prevChapterId;
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
		instance.setId(result.getInt("id"));
		instance.setPrevChapterId(result.getInt("prev_chapter_id"));
		instance.setTitle(result.getString("title"));
		instance.setBody(result.getString("body"));
		return instance;
	}
}
