package org.ivavin.eventweather.model;

import java.io.Serializable;

public class EventForecast implements Serializable {

	private static final long serialVersionUID = -5296765555290834726L;
	private String description;
	private Double temp;
	private Integer humidity;

	public EventForecast() {
	}

	public EventForecast(final String description, final Double temp, final Integer humidity) {
		super();
		this.description = description;
		this.temp = temp;
		this.humidity = humidity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(final Double temp) {
		this.temp = temp;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(final Integer humidity) {
		this.humidity = humidity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((humidity == null) ? 0 : humidity.hashCode());
		result = prime * result + ((temp == null) ? 0 : temp.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EventForecast other = (EventForecast) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (humidity == null) {
			if (other.humidity != null) {
				return false;
			}
		} else if (!humidity.equals(other.humidity)) {
			return false;
		}
		if (temp == null) {
			if (other.temp != null) {
				return false;
			}
		} else if (!temp.equals(other.temp)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "EventForecast [description=" + description + ", temp=" + temp + ", humidity=" + humidity + "]";
	}

}
