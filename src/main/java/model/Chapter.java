package model;

/**
 * This object represents a chapter in the book.
 *
 * The chapter consists of a title and a message that are displayed
 * when the chapter begins.
 */
public class Chapter {

	private String title;
	private String message;

	public Chapter(String title, String message) {
		this.title = title;
		this.message = message;
	}	

	public String getTitle() {
		return title;
	}
	
	public String getMessage() {
		return message;
	}
}
