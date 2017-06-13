package org.ivavin.eventweather.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResponse implements Serializable {

	private static final long serialVersionUID = -7217982303445577381L;

	private String pageCount;

	private Events events;

	public EventResponse() {
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(final String pageCount) {
		this.pageCount = pageCount;
	}

	public Events getEvents() {
		return events;
	}

	public void setEvents(final Events events) {
		this.events = events;
	}

	@Override
	public String toString() {

		return "EventRequest{" + "pageCount='" + pageCount + '\'' + ", events=" + events.toString() + '}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		result = prime * result + ((pageCount == null) ? 0 : pageCount.hashCode());
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
		EventResponse other = (EventResponse) obj;
		if (events == null) {
			if (other.events != null) {
				return false;
			}
		} else if (!events.equals(other.events)) {
			return false;
		}
		if (pageCount == null) {
			if (other.pageCount != null) {
				return false;
			}
		} else if (!pageCount.equals(other.pageCount)) {
			return false;
		}
		return true;
	}

}
