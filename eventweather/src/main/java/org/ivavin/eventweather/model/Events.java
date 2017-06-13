package org.ivavin.eventweather.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Events implements Serializable {

	private static final long serialVersionUID = -7444776712396688485L;

	private List<Event> event = null;

	public Events() {
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(final List<Event> event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Events{" + "event='" + event.toString() + '}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
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
		Events other = (Events) obj;
		if (event == null) {
			if (other.event != null) {
				return false;
			}
		} else if (!event.equals(other.event)) {
			return false;
		}
		return true;
	}

}
