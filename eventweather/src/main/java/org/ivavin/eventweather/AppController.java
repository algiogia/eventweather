package org.ivavin.eventweather;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ivavin.eventweather.exception.EventServiceException;
import org.ivavin.eventweather.model.Event;
import org.ivavin.eventweather.model.Forecast;
import org.ivavin.eventweather.service.EventService;
import org.ivavin.eventweather.service.WeatherService;
import org.ivavin.eventweather.service.WeatherServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	private static final Logger LOG = Logger.getLogger(AppController.class.getName());

	private static final String LOCATION = "london";

	private static final long MAX_DAYS_GAP = 3 * 24 * 60 * 60 * 1000;

	@Autowired
	private final EventService eventService;
	@Autowired
	private final WeatherService weatherService;

	public AppController(final EventService eventService, final WeatherService weatherService) {
		this.eventService = eventService;
		this.weatherService = weatherService;
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
			result.put("all", eventService.getEvents(LOCATION, null));
		} else {
			for (String category : categories) {
				result.put(category, eventService.getEvents(LOCATION, category));
			}
		}
		if (!result.isEmpty()) {
			provideForecast(result);
		}

		return result;
	}

	private void provideForecast(final Map<String, List<Event>> eventsByCategory) {

		try {
			List<Forecast> forecasts = weatherService.getWeather(LOCATION);

			for (List<Event> events : eventsByCategory.values()) {
				for (Event event : events) {
					setForecast(event, forecasts);
				}
			}
		} catch (WeatherServiceException e) {
			LOG.error("Unable to get weather forecast", e);
		}
	}

	/**
	 * Try to find the forecast closest to the event date. Ignore forecasts more
	 * than 3 days afar.
	 *
	 */
	private void setForecast(final Event event, final List<Forecast> forecasts) {

		long minDistance = -1;
		for (Forecast forecast : forecasts) {
			long distance = distance(event.getStartTime(), forecast.getDate());
			if (distance <= MAX_DAYS_GAP) {
				if (distance < minDistance || minDistance == -1) {
					minDistance = distance;
				}
				event.setForecast(forecast);
			}
		}
	}

	private long distance(final Date eventTime, final Date forecastTime) {
		return Math.abs(eventTime.getTime() - forecastTime.getTime());
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
