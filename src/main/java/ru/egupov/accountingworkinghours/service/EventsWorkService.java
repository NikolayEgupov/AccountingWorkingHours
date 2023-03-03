package ru.egupov.accountingworkinghours.service;

import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.dto.EventWorkDTO;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.model.EventWork;
import ru.egupov.accountingworkinghours.repository.EventWorkRepository;
import ru.egupov.accountingworkinghours.util.mapper.EventWorkMapper;

import java.util.Date;
import java.util.List;

@Service
public class EventsWorkService {

    private final EventWorkRepository eventWorkRepository;
    private final EventWorkMapper eventWorkMapper;

    public EventsWorkService(EventWorkRepository eventWorkRepository, EventWorkMapper eventWorkMapper) {
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
        if (eventWork.getDate() == null)
            eventWork.setDate(new Date());
        eventWorkRepository.save(eventWork);
    }

    public void saveFromDto(EventWorkDTO eventWorkDTO){
        save(eventWorkMapper.toEntity(eventWorkDTO));
    }

    public void deleteById(int id){
        eventWorkRepository.deleteById(id);
    }

    public List<EventWork> findByEmployeeIdDateStartDateEnd(Integer employeeId, Date dateStart, Date dateEnd){
        return eventWorkRepository.findByEmployee_IdAndDateBetween(employeeId, dateStart, dateEnd);
    }

    public List<EventWorkDTO> findByEmployeeIdDateStartDateEndInDto(Integer employeeId, Date dateStart, Date dateEnd) {
        return eventWorkMapper.toDto(findByEmployeeIdDateStartDateEnd(employeeId, dateStart, dateEnd));
    }

    public List<EventWork> findByEmployeeAndDateBetweenOrderByDate(Employee employee, Date dateStart, Date dateEnd){
        return eventWorkRepository.findByEmployeeAndDateBetweenOrderByDate(employee, dateStart, dateEnd);
    }
}
