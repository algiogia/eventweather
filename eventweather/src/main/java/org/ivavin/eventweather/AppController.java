package org.ivavin.eventweather;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ivavin.eventweather.exception.EventServiceException;
import org.ivavin.eventweather.model.Event;
import org.ivavin.eventweather.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	private static final Logger LOG = Logger.getLogger(AppController.class.getName());

	@Autowired
	private final EventService eventService;

	public AppController(final EventService eventService) {
		this.eventService = eventService;
	}

	@RequestMapping("/")
	public String events(final Map<String, Object> model) {
		try {
			model.put("events", getEvents());
		} catch (EventServiceException ex) {
			LOG.error("Unable to retrieve the events", ex);
			model.put("error", "There was a problem while getting the list of event. Please try again later");
		}
		return "events";
	}

	private Map<String, List<Event>> getEvents() throws EventServiceException {

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
	 * 
	 * @throws EventServiceException
	 */
	private List<String> getCategories() throws EventServiceException {

		return eventService.getCategories();
	}
}
