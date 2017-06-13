package org.ivavin.eventweather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ivavin.eventweather.exception.EventServiceExcception;
import org.ivavin.eventweather.model.Event;
import org.ivavin.eventweather.service.EventService;
import org.junit.Test;

public class AppControllerTest {

	private static final Event MUSIC_EVENT_1 = new Event();
	private static final Event MUSIC_EVENT_2 = new Event();

	private static final Event FOOD_EVENT_1 = new Event();
	private static final Event FOOD_EVENT_2 = new Event();

	private static final Event COMEDY_EVENT_1 = new Event();
	private static final Event COMEDY_EVENT_2 = new Event();

	EventService eventService = mock(EventService.class);

	@Test
	public void test() throws EventServiceExcception {

		List<Event> musicEvents = Arrays.asList(MUSIC_EVENT_1, MUSIC_EVENT_2);
		List<Event> foodEvents = Arrays.asList(FOOD_EVENT_1, FOOD_EVENT_2);
		List<Event> comedyEvents = Arrays.asList(COMEDY_EVENT_1, COMEDY_EVENT_2);
		// List<Event> allEvents = Arrays.asList(MUSIC_EVENT_1, MUSIC_EVENT_2,
		// FOOD_EVENT_1, FOOD_EVENT_2, COMEDY_EVENT_1,
		// COMEDY_EVENT_2);

		when(eventService.getEvents("london", "music")).thenReturn(musicEvents);
		when(eventService.getEvents("london", "food")).thenReturn(foodEvents);
		when(eventService.getEvents("london", "comedy")).thenReturn(comedyEvents);

		AppController appController = new AppController(eventService);

		Map<String, Object> model = new HashMap<>();
		appController.events(model);
		assertTrue("The model should contain the events", model.containsKey("events"));
		@SuppressWarnings("unchecked")
		Map<String, List<Event>> eventsByCategory = (Map<String, List<Event>>) model.get("events");
		assertEquals("The events map should contain 3 categories", 3, eventsByCategory.size());
		assertEquals("There should be 2 music events", musicEvents, eventsByCategory.get("music"));
		assertEquals("There should be 2 food events", musicEvents, eventsByCategory.get("food"));
		assertEquals("There should be 2 comedy events", musicEvents, eventsByCategory.get("comedy"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testServiceUnavailable() throws EventServiceExcception {

		when(eventService.getEvents(anyString(), anyString())).thenThrow(EventServiceExcception.class);

		AppController appController = new AppController(eventService);
		Map<String, Object> model = new HashMap<>();
		appController.events(model);
		assertEquals("The model should contain an error message",
				"There was a problem while getting the list of event. Please try again later", model.get("error"));

	}
}
