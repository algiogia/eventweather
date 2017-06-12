package org.ivavin.eventweather.service;

import java.util.List;

import org.ivavin.eventweather.model.Event;

/**
 * The EventService connects to an API that provides events information.
 *
 * @author Ivano
 *
 */
public interface EventService {

	/**
	 * Returns a list of events
	 * 
	 * @param location
	 *            the location for the events, not <code>null</code>
	 * @param category
	 *            the optional category
	 * @param params
	 *            optional search parameters
	 * @return a list of {@link Event}s
	 */
	List<Event> getEvents(String location, String category, String... queryParams);

	/**
	 * Returns the list of available categories
	 * 
	 * @return a list of Strings
	 */
	List<String> getCategories();
}
