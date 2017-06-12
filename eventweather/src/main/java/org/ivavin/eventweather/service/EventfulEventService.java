package org.ivavin.eventweather.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.ivavin.eventweather.model.Event;
import org.ivavin.eventweather.model.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation of EventService which uses the EventFul API.
 * http://api.eventful.com/
 *
 * @author Ivano
 *
 */
@Service
public class EventfulEventService implements EventService {

	private final static String API_URL = "http://api.eventful.com/json/events/search";

	private final RestTemplate restTemplate;
	private final String appKey;

	/**
	 * @param appKey
	 *            the API's authentication key
	 */
	@Autowired
	public EventfulEventService(final Environment env) {
		appKey = env.getProperty("eventful.app_key");
		restTemplate = new RestTemplate();
		initConvertersForEventful(restTemplate);
	}

	@Override
	public List<Event> getEvents(final String location, final String category, final String... queryParams) {
		EventResponse eventResponse = restTemplate.getForObject(buildRequestURL(location, category, queryParams),
				EventResponse.class);

		List<Event> events = eventResponse.getEvents().getEvent();
		if (StringUtils.isNotBlank(category)) {
			setCategory(events, category);
		}
		return events;
	}

	private void setCategory(final List<Event> events, final String category) {
		for (Event event : events) {
			event.setCategory(category);
		}
	}

	private String buildRequestURL(final String location, final String category, final String[] queryParams) {

		String result = API_URL + "?app_key=" + appKey + "&location=" + location;

		if (StringUtils.isNotBlank(category)) {
			result += "&category=" + category;
		}

		/* Extra query params ignored for now */

		return result;
	}

	/**
	 * EventFul returns the wrong MIME type ("text/javascript" instead of
	 * "application/json"), so we need to adjust the converter's mapping.
	 *
	 * @param restTemplate
	 */
	private static void initConvertersForEventful(final RestTemplate restTemplate) {
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		List<MediaType> jsonMediaTypes = Arrays.asList(
				new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET),
				new MediaType("text", "javascript", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET));

		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
				jsonConverter.setObjectMapper(new ObjectMapper());
				jsonConverter.setSupportedMediaTypes(Collections.unmodifiableList(jsonMediaTypes));
			}
		}
	}

	@Override
	public List<String> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}
}
