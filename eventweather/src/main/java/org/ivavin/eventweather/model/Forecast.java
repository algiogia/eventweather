package org.ivavin.eventweather.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast implements Serializable {

	private static final long serialVersionUID = -1780403011181211582L;

	@JsonProperty("dt_txt")
	private Date date;

	private Main main;

	private final java.util.List<Weather> weather = null;

	public Forecast() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(final Main main) {
		this.main = main;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	@Override
	public String toString() {
		return "Forecast [main=" + main + ", weather=" + weather + "]";
	}

}
