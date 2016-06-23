package das;

import java.util.Map;
import java.util.HashMap;
import model.Page;

public class PageDataService {
	
	private Map<Integer, Page> pages;

	public PageDataService() {
		pages = new HashMap<Integer, Page>();

		//TODO: REMOVE - This is for testing only
		Page initialPage = new Page(0, "This is where it starts", true);
		Page page0 = new Page(1, "Dragonfly Engine", true);
		Page page1 = new Page(1, "Page 1", true);
		Page page2 = new Page(1, "Page 2", true);
		Page page3 = new Page(1, "Page 3", true);
		Page page4 = new Page(1, "Page 4", true);

		initialPage.addPageBinding("start", 0);

		page0.addPageBinding("go one", 1);
		page0.addPageBinding("go two", 2);
		page0.addPageBinding("go three", 3);
		page0.addPageBinding("go four", 4);
		
		page1.addPageBinding("go zero", 0);
		page1.addPageBinding("go two", 2);
		page1.addPageBinding("go three", 3);
		page1.addPageBinding("go four", 4);
		
		page2.addPageBinding("go one", 1);
		page2.addPageBinding("go zero", 0);
		page2.addPageBinding("go three", 3);
		page2.addPageBinding("go four", 4);
		
		page3.addPageBinding("go one", 1);
		page3.addPageBinding("go two", 2);
		page3.addPageBinding("go zero", 0);
		page3.addPageBinding("go four", 4);
		
		page4.addPageBinding("go one", 1);
		page4.addPageBinding("go two", 2);
		page4.addPageBinding("go three", 3);
		page4.addPageBinding("go zero", 0);

		createPage(-1, initialPage);
		createPage(0, page0);
		createPage(1, page1);
		createPage(2, page2);
		createPage(3, page3);
		createPage(4, page4);
	}

	public Page getPage(Integer id) {
		return pages.get(id);
	}

	public Page createPage(Integer id, Page page) {
		return pages.put(id, page);
	}
}
