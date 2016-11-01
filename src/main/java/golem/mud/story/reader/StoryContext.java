package golem.mud.story.reader;

import golem.mud.common.telnet.TelnetRenderer;
import golem.mud.story.das.StoryAggregateDataService;

public class StoryContext {

	public final TelnetRenderer renderer;
	public final StoryAggregateDataService services;

	public StoryContext(TelnetRenderer renderer, StoryAggregateDataService services) {
		this.renderer = renderer;
		this.services = services;
	}
}
