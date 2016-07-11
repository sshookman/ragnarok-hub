package das;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import model.Page;

public class PageDataService {

	private Connection story;

	public PageDataService(Connection story) {
		this.story = story;
	}

	public List<Page> readPages(Integer chapterId) {
		List<Page> pages = new ArrayList<Page>();
		try {
			Statement stmt = story.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM page WHERE chapter_id IS " + chapterId);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				chapterId = rs.getInt("chapter_id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				Page page = new Page(id, chapterId, title, body);
				pages.add(page);
			}
			rs.close();
			stmt.close();
		} catch (SQLException exception) {
			System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
      		System.exit(0);
		}
		return pages;
	}
}
