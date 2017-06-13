package org.ivavin.eventweather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ivavin.eventweather.exception.EventServiceException;
import org.ivavin.eventweather.model.Event;
import org.ivavin.eventweather.model.Forecast;
import org.ivavin.eventweather.model.Main;
import org.ivavin.eventweather.model.Weather;
import org.ivavin.eventweather.service.EventService;
import org.ivavin.eventweather.service.WeatherService;
import org.ivavin.eventweather.service.WeatherServiceException;
import org.junit.Test;

public class AppControllerTest {

	private static final String LOCATION = "london";
	private static final String CATEGORY_COMEDY = "comedy";
	private static final String CATEGORY_FOOD = "food";
	private static final String CATEGORY_MUSIC = "music";
	private static final Date DATE_1;
	private static final Date DATE_2;
	private static final Date DATE_3;
	private static final Date DATE_4;
	private static final Date DATE_5;
	static {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2017, 1, 1);
		DATE_1 = cal.getTime();
		cal.set(2017, 1, 2);
		DATE_2 = cal.getTime();
		cal.set(2017, 1, 3);
		DATE_3 = cal.getTime();
		cal.set(2017, 2, 1);
		DATE_4 = cal.getTime();
		cal.set(2017, 3, 1);
		DATE_5 = cal.getTime();
	}

	private static final Event MUSIC_EVENT_1 = new Event();
	private static final Event MUSIC_EVENT_2 = new Event();
	static {
		MUSIC_EVENT_1.setCityName(LOCATION);
		MUSIC_EVENT_1.setStartTime(DATE_1);
		MUSIC_EVENT_2.setCityName(LOCATION);
		MUSIC_EVENT_2.setStartTime(DATE_2);
	}

	private static final Event FOOD_EVENT_1 = new Event();
	private static final Event FOOD_EVENT_2 = new Event();
	static {
		FOOD_EVENT_1.setCityName(LOCATION);
		FOOD_EVENT_1.setStartTime(DATE_2);
		FOOD_EVENT_2.setCityName(LOCATION);
		FOOD_EVENT_2.setStartTime(DATE_4);
	}

	private static final Event COMEDY_EVENT_1 = new Event();
	private static final Event COMEDY_EVENT_2 = new Event();
	static {
		COMEDY_EVENT_1.setCityName(LOCATION);
		COMEDY_EVENT_1.setStartTime(DATE_3);
		COMEDY_EVENT_2.setCityName(LOCATION);
		COMEDY_EVENT_2.setStartTime(DATE_5);
	}

	private static final Forecast DAY_1 = new Forecast();
	private static final Forecast DAY_2 = new Forecast();
	private static final Forecast DAY_3 = new Forecast();
	static {
		DAY_1.setDate(DATE_1);
		DAY_1.setMain(new Main());
		DAY_1.getWeather().add(new Weather());
		DAY_2.setDate(DATE_2);
		DAY_2.setMain(new Main());
		DAY_2.getWeather().add(new Weather());
		DAY_3.setDate(DATE_3);
		DAY_3.setMain(new Main());
		DAY_3.getWeather().add(new Weather());
	}

	EventService eventService = mock(EventService.class);
	WeatherService weatherService = mock(WeatherService.class);

	@Test
	public void test() throws EventServiceException, WeatherServiceException {

		/* Setup EventService mock */
		List<String> categories = Arrays.asList(CATEGORY_MUSIC, CATEGORY_FOOD, CATEGORY_COMEDY);
		List<Event> musicEvents = Arrays.asList(MUSIC_EVENT_1, MUSIC_EVENT_2);
		List<Event> foodEvents = Arrays.asList(FOOD_EVENT_1, FOOD_EVENT_2);
		List<Event> comedyEvents = Arrays.asList(COMEDY_EVENT_1, COMEDY_EVENT_2);

		when(eventService.getCategories()).thenReturn(categories);
		when(eventService.getEvents(LOCATION, CATEGORY_MUSIC)).thenReturn(musicEvents);
		when(eventService.getEvents(LOCATION, CATEGORY_FOOD)).thenReturn(foodEvents);
		when(eventService.getEvents(LOCATION, CATEGORY_COMEDY)).thenReturn(comedyEvents);

		/* Setup WeatherService mock */

		List<Forecast> forecast = Arrays.asList(DAY_1, DAY_2, DAY_3);
		when(weatherService.getWeather(LOCATION)).thenReturn(forecast);

		AppController appController = new AppController(eventService, weatherService);

		Map<String, Object> model = new HashMap<>();
		appController.events(model);
		assertTrue("The model should contain the events", model.containsKey("events"));
		@SuppressWarnings("unchecked")
		Map<String, List<Event>> eventsByCategory = (Map<String, List<Event>>) model.get("events");
		assertEquals("The events map should contain 3 categories", 3, eventsByCategory.size());
		assertEquals("There should be 2 music events", musicEvents, eventsByCategory.get(CATEGORY_MUSIC));
		assertEquals("There should be 2 food events", foodEvents, eventsByCategory.get(CATEGORY_FOOD));
		assertEquals("There should be 2 comedy events", comedyEvents, eventsByCategory.get(CATEGORY_COMEDY));

		assertTrue("The forecast for MUSIC_EVENT_1 should be available", MUSIC_EVENT_1.getForecast() != null);
		assertTrue("The forecast for COMEDY_EVENT_2 should not be available", COMEDY_EVENT_2.getForecast() == null);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEventServiceUnavailable() throws EventServiceException {

		when(eventService.getEvents(anyString(), anyString())).thenThrow(EventServiceException.class);

		AppController appController = new AppController(eventService, weatherService);
		Map<String, Object> model = new HashMap<>();
		appController.events(model);
		assertEquals("The model should contain an error message",
				"There was a problem while getting the list of event. Please try again later", model.get("error"));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testWeatherServiceUnavailable() throws EventServiceException, WeatherServiceException {

		/* Setup EventService mock */
		List<String> categories = Arrays.asList(CATEGORY_MUSIC);
		List<Event> musicEvents = Arrays.asList(MUSIC_EVENT_1, MUSIC_EVENT_2);

		when(eventService.getCategories()).thenReturn(categories);
		when(eventService.getEvents(LOCATION, CATEGORY_MUSIC)).thenReturn(musicEvents);

		/* Setup WeatherService mock */
		when(weatherService.getWeather(anyString())).thenThrow(WeatherServiceException.class);

		AppController appController = new AppController(eventService, weatherService);

		Map<String, Object> model = new HashMap<>();
		appController.events(model);
		assertTrue("The model should contain the events", model.containsKey("events"));
		Map<String, List<Event>> eventsByCategory = (Map<String, List<Event>>) model.get("events");
		assertEquals("The events map should contain 1 category", 1, eventsByCategory.size());
		assertEquals("There should be 2 music events", musicEvents, eventsByCategory.get(CATEGORY_MUSIC));
	}
}
