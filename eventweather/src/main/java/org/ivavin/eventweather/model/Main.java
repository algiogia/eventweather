package org.ivavin.eventweather.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Main implements Serializable {

	private static final long serialVersionUID = 8335249247816591610L;

	private Double temp;
	private Integer humidity;

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
		Main other = (Main) obj;
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
		return "Main [temp=" + temp + ", humidity=" + humidity + "]";
	}

}