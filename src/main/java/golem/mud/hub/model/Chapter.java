package golem.mud.hub.model;

public class Chapter implements DataObject {

	private Integer id;
	private Integer prevChapterId;
	private String title;
	private String body;

	public Chapter(Integer id, Integer prevChapterId, String title, String body) {
		this.id = id;
		this.prevChapterId = prevChapterId;
		this.title = title;
		this.body = body;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
