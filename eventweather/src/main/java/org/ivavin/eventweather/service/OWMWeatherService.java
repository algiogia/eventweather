package org.ivavin.eventweather.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.ivavin.eventweather.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation of {@link WeatherService} that uses OpenWeatherMap API.
 * http://api.openweathermap.org
 *
 * @author Ivano
 *
 */
public class OWMWeatherService implements WeatherService {

	private static final String baseURL = "";

	private final RestTemplate restTemplate;
	private final String appId;

	@Autowired
	public OWMWeatherService(final Environment env) {
		appId = env.getProperty("openweathermap.appid");
		restTemplate = new RestTemplate();
		initConverters(restTemplate);
	}

	@Override
	public List<Forecast> getWeather(final String location) {

		return null;
	}

	/**
	 * Adjust date format to OWM
	 *
	 * @param restTemplate
	 */
	private static void initConverters(final RestTemplate restTemplate) {
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		List<MediaType> jsonMediaTypes = Arrays.asList(
				new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET),
				new MediaType("text", "javascript", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET));

		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
				jsonConverter.setObjectMapper(new ObjectMapper());
				jsonConverter.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
				jsonConverter.setSupportedMediaTypes(Collections.unmodifiableList(jsonMediaTypes));
			}
		}
	}
}
