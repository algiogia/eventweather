package org.ivavin.eventweather;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ivavin.eventweather.model.Event;
import org.ivavin.eventweather.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@Autowired
	private final EventService eventService;

	public AppController(final EventService eventService) {
		this.eventService = eventService;
	}

	@RequestMapping("/")
	public String events(final Map<String, Object> model) {
		model.put("events", getEvents());
		return "events";
	}

	private Map<String, List<Event>> getEvents() {

		Map<String, List<Event>> result = new LinkedHashMap<String, List<Event>>();
		List<String> categories = getCategories();
		if (categories.isEmpty()) {
			result.put("all", eventService.getEvents("london", null));
		} else {
			for (String category : categories) {
				result.put(category, eventService.getEvents("london", category));
			}
		}

		return result;
	}

	/**
	 * Returns a fixed list of categories but could invoke a service to retrieve
	 * them.
	 */
	private List<String> getCategories() {

		return Arrays.asList("music", "food", "comedy");
	}
}
