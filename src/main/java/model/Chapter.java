package model;

/**
 * This object represents a chapter in the book.
 *
 * The chapter consists of a title and a message that are displayed
 * when the chapter begins.
 */
public class Chapter {

	private Integer id;
	private Integer prevChapterId;
	private String title;
	private String message;

	public Chapter(Integer id, Integer prevChapterId, String title, String message) {
		this.id = id;
		this.prevChapterId = prevChapterId;
		this.title = title;
		this.message = message;
	}	

	public Integer getId() {
		return id;
	}

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
