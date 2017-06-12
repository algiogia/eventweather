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

  public void setEvent(List<Event> event) {
    this.event = event;
  }

  @Override
  public String toString() {
    return "Events{" + "event='" + event.toString() + '}';
  }
}
