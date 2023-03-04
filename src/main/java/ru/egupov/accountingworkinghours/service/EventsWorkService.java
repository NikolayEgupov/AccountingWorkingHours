package ru.egupov.accountingworkinghours.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.dto.EventWorkDTO;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.model.EventWork;
import ru.egupov.accountingworkinghours.repository.EventWorkRepository;
import ru.egupov.accountingworkinghours.util.mapper.EventWorkMapper;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EventsWorkService {

    private final EventWorkRepository eventWorkRepository;
    private final EventWorkMapper eventWorkMapper;
    private final EmployeesService employeesService;

    public List<EventWorkDTO> getByEmployeeIdDateStartDateEndInDto(int employeeId, Date dateStart, Date dateEnd) {
        return eventWorkMapper.toDto(
                getByEmployeeAndDateBetweenOrderByDate(employeesService.getById(employeeId), dateStart, dateEnd));
    }

    public List<EventWork> getByEmployeeAndDateBetweenOrderByDate(Employee employee, Date dateStart, Date dateEnd){
        return eventWorkRepository.findByEmployeeAndDateBetweenOrderByDate(employee, dateStart, dateEnd);
    }

    public void saveFromDto(EventWorkDTO eventWorkDTO){
        save(eventWorkMapper.toEntity(eventWorkDTO));
    }

    public void save(EventWork eventWork){
        if (eventWork.getDate() == null)
            eventWork.setDate(new Date());
        eventWorkRepository.save(eventWork);
    }

    public void deleteById(int id){
        eventWorkRepository.deleteById(id);
    }
}
