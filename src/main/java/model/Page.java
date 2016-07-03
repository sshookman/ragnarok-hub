package model;

import java.util.Map;
import java.util.HashMap;

/**
 * This object represents a single page in the book.
 *
 * It consists of a message and an optional user input prompt. It also
 * contains a map of all of the pages that can be accessed from this page.
 * A regex is used as the key to evaluate user input against the available
 * pages in the map.
 */
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
