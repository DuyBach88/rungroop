package com.rungroop.web.service.impl;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.Event;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.repository.EvenRepository;
import com.rungroop.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroop.web.mapper.EventMapper.mapToEventDto;
import static com.rungroop.web.mapper.EventMapper.maptoEvent;

@Service
public class EventServiceimpl implements EventService {
    private EvenRepository evenRepository;
    private ClubRepository clubRepository;
    @Autowired
    public EventServiceimpl(EvenRepository evenRepository, ClubRepository clubRepository) {
        this.evenRepository = evenRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
     Club club = clubRepository.findById(clubId).get();
     Event event = maptoEvent(eventDto);
     event.setClub(club);
     evenRepository.save(event);

    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = evenRepository.findAll();
        return events.stream()
                .map(event -> mapToEventDto(event))
                .collect(Collectors.toList());
    }

    @Override
    public EventDto findEventById(Long eventId) {
       Event event = evenRepository.findById(eventId).get();
       return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
       Event event = maptoEvent(eventDto);
       evenRepository.save(event);
    }

    @Override
    public void delete(Long eventId) {
        evenRepository.deleteById(eventId);
    }

//    private Event maptoEvent(EventDto eventDto) {
//         return Event.builder()
//                 .id(eventDto.getId())
//                 .name(eventDto.getName())
//                 .startTime(eventDto.getStartTime())
//                 .endTime(eventDto.getEndTime())
//                 .type(eventDto.getType())
//                 .photoUrl(eventDto.getPhotoUrl())
//                 .createdOn(eventDto.getCreatedOn())
//                 .updatedOn(eventDto.getUpdatedOn())
//                 .build();
//
//    }
}
