package org.ivavin.eventweather.service;

public class WeatherServiceException extends Exception {

	private static final long serialVersionUID = 3303508304042419289L;

	public WeatherServiceException(final String message) {
		super(message);
	}

	public WeatherServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
