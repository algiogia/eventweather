package org.ivavin.eventweather.service;

import java.util.Date;

import org.ivavin.eventweather.model.Weather;

public interface WeatherService {

	Weather getWeather(String location, Date date);
}
