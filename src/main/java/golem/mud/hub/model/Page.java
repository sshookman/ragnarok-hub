package golem.mud.hub.model;

import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Page extends DataObject {

	private Integer id;
	private Integer chapterId;
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

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
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

	public void setBody(String title) {
		this.body = body;
	}

	@Override
	public String getTable() {
		return "page";
	}

	@Override
	public Page instance(ResultSet result) throws SQLException {
		Page instance = new Page();
		instance.setId(result.getInt("id"));
		instance.setChapterId(result.getInt("chapter_id"));
		instance.setTitle(result.getString("title"));
		instance.setBody(result.getString("body"));
		return instance;
	}
}
