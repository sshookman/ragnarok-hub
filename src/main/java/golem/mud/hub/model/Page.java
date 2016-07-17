package golem.mud.hub.model;

import java.util.Map;
import java.util.HashMap;

public class Page {

	private Integer id;
	private Integer chapterId;
	private String title;
	private String body;

	public Page(Integer id, Integer chapterId, String title, String body) {
		this.id = id;
		this.chapterId = chapterId;
		this.title = title;
		this.body = body;
	}

	public Integer getId() {
		return id;
	}
	
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

	public void setTitle() {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody() {
		this.body = body;
	}
}
