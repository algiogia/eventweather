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

  public void setPageCount(String pageCount) {
    this.pageCount = pageCount;
  }

  public Events getEvents() {
    return events;
  }

  public void setEvents(Events events) {
    this.events = events;
  }

  @Override
  public String toString() {

    return "EventRequest{" + "pageCount='" + pageCount + '\'' + ", events=" + events.toString() + '}';
  }
}
