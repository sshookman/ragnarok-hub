package codepoet.ragnarok.hub.view;

import codepoet.ragnarok.das.model.StoryDO;
import codepoet.ragnarok.hub.model.Request;
import codepoet.ragnarok.render.Renderer;
import java.util.List;
import java.util.Map;

public class LibraryView implements View {

	private static final String TABLE_TEMPLATE = ""
			+ " <<{{P}}______________________________{{NAME}}______________________________{{N}}>>\n"
			+ "\n"
			+ " +--------+-------------+------------+----------------------+----------------+\n"
			+ " |   ID   |    Title    |   Author   |         Tags         |     Rating     |\n"
			+ " +--------+-------------+------------+----------------------+----------------+\n"
			+ " | [6]    | Game This   | Joe Bob    | Horror, Mystery      |   ***-- (29)   |\n"
			+ " | [7]    | Game That   | Moe Bob    | Horror, Action       |   ****- (24)   |\n"
			+ " | [8]    | Game Other  | Joe Lob    | Horror, Adventure    |   **--- (93)   |\n"
			+ " | [9]    | Game Also   | Moe Sob    | Fantasy, Mystery     |   ***** (1)    |\n"
			+ " | [10]   | Game Not    | Joe Job    | Action, Mystery      |   ****- (352)  |\n"
			+ " +--------+-------------+------------+----------------------+----------------+\n"
			+ "\n"
			+ "                                                                  Page 2 of 26\n"
			+ "";

	@Override
	public Request render(Renderer renderer, Map<String, Object> model) {
		List<StoryDO> stories = (List<StoryDO>) model.get("stories");

		stories.forEach((story) -> {
			renderer.write(story.getName());
			renderer.endl(1);
		});

		renderer.endl(2);
		String input = renderer.prompt();

		Request request = new Request.Builder()
				.controller("welcome")
				.build();

		return request;
	}
}
