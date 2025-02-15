package com.rungroop.web.controller;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;
import com.rungroop.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping("/events")
    public String eventList(Model model) {
      List<EventDto> events = eventService.findAllEvents();
      model.addAttribute("events", events);
      return "events-list";
    }

    @GetMapping("/events/{eventId}")
    public String viewEvents(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "events-details";
    }

    @GetMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId, Model model) {
     EventDto eventDto = eventService.findEventById(eventId);
     model.addAttribute("event", eventDto);
     return "events-edit";
    }
    // Update
    @PostMapping ("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId, @Validated
    @ModelAttribute("club") EventDto event,
                             BindingResult result , Model model) {
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "events-edit";
        }
        EventDto eventDto = eventService.findEventById(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);
        return "redirect:/clubs";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEvenForm (@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @PostMapping("/events/{clubId}")
    public String createEven (@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto ,
                              Model model ,BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "club-create";
        }
        eventService.createEvent(clubId , eventDto);
        return "redirect:/clubs/" + clubId;
    }
    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
         eventService.delete(eventId);
         return "redirect:/events";

    }
}
