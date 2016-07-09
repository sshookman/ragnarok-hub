package das;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChapterDataService {

	private Connection story;

	public ChapterDataService(Connection story) {
		this.story = story;
	}

	public String readChapter(Integer prevChapterId) {
		String body = "";
		try {
			Statement stmt = story.createStatement();
    	  	ResultSet rs = stmt.executeQuery("SELECT * FROM chapter WHERE prev_chapter_id IS NULL");
     		while (rs.next()) {
         		body += rs.getString("body");
      		}
      		rs.close();
      		stmt.close();
		} catch (SQLException exception) {
			System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
      		System.exit(0);
		}
		return body;
	}
}
