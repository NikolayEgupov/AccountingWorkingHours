package ru.egupov.accountingworkinghours.service;

import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.dto.EventWorkDTO;
import ru.egupov.accountingworkinghours.model.EventWork;
import ru.egupov.accountingworkinghours.repository.EventWorkRepository;
import ru.egupov.accountingworkinghours.util.mapper.EventWorkMapper;

import java.util.List;

@Service
public class EventWorkService {

    private final EventWorkRepository eventWorkRepository;
    private final EventWorkMapper eventWorkMapper;

    public EventWorkService(EventWorkRepository eventWorkRepository, EventWorkMapper eventWorkMapper) {
        this.eventWorkRepository = eventWorkRepository;
        this.eventWorkMapper = eventWorkMapper;
    }

    public List<EventWork> findAll(){
        return eventWorkRepository.findAll();
    }

    public List<EventWorkDTO> findAllInDto(){
        return eventWorkMapper.toDto(findAll());
    }

    public void save(EventWork eventWork){
        eventWorkRepository.save(eventWork);
    }

    public void saveFromDto(EventWorkDTO eventWorkDTO){
        save(eventWorkMapper.toEntity(eventWorkDTO));
    }
}
