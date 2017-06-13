package org.ivavin.eventweather.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast implements Serializable {

	private static final long serialVersionUID = -1780403011181211582L;

	private Main main;

	private final java.util.List<Weather> weather = null;

	public Forecast() {
	}

	public Main getMain() {
		return main;
	}

	public void setMain(final Main main) {
		this.main = main;
	}

	public java.util.List<Weather> getWeather() {
		return weather;
	}

	@Override
	public String toString() {
		return "Forecast [main=" + main + ", weather=" + weather + "]";
	}

}
