package org.ivavin.eventweather;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ivavin.eventweather.model.Event;
import org.ivavin.eventweather.service.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

  private final EventService eventService;
  private List<String> categories;

  public AppController(EventService newEventService) {
    eventService = newEventService;
  }

  @RequestMapping("/")
  public String events(Map<String, Object> model) {
    model.put("message", getEvents());
    return "events";
  }

  public void setCategories(List<String> newCategories) {
    if (newCategories != null) {
      categories = new ArrayList<String>(newCategories);
    }
  }

  private Map<String, List<Event>> getEvents() {

    Map<String, List<Event>> result = new LinkedHashMap<String, List<Event>>();

    if (categories.isEmpty()) {
      result.put("all", eventService.getEvents("location", null));
    } else {
      for (String category : categories) {
        result.put(category, eventService.getEvents("location", category));
      }
    }

    return result;
  }
}
