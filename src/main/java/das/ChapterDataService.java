package das;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import model.Chapter;

public class ChapterDataService {

	private Connection story;

	public ChapterDataService(Connection story) {
		this.story = story;
	}

	public List<Chapter> readChapters(Integer prevChapterId) {
		List<Chapter> chapters = new ArrayList<Chapter>();
		try {
			Statement stmt = story.createStatement();
			String prevChapter = (prevChapterId == null) ? "NULL" : prevChapterId.toString();
    	  	ResultSet rs = stmt.executeQuery("SELECT * FROM chapter WHERE prev_chapter_id IS " + prevChapter);
     		while (rs.next()) {
				Integer id = rs.getInt("id");
				prevChapterId = rs.getInt("prev_chapter_id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				Chapter chapter = new Chapter(id, prevChapterId, title, body);
				chapters.add(chapter);
      		}
      		rs.close();
      		stmt.close();
		} catch (SQLException exception) {
			System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
      		System.exit(0);
		}
		return chapters;
	}
}
