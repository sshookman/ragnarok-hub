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

	private Integer chapterId;
	private String message;
	private boolean prompt;
	private Map<String, Integer> pageBindings;

	public Page(Integer chapterId, String message, boolean prompt) {
		this.chapterId = chapterId;
		this.message = message;
		this.prompt = prompt;
		this.pageBindings = new HashMap<String, Integer>();
	}

	public Integer getChapterId() {
		return chapterId;
	}
	
	public String getMessage() {
		return message;
	}

	public boolean isPrompt() {
		return prompt;
	}

	public Integer getPageBinding(String search) {
		return pageBindings.get(search);
	}

	public void addPageBinding(String key, Integer pageId) {
		pageBindings.put(key, pageId);
	}
}
