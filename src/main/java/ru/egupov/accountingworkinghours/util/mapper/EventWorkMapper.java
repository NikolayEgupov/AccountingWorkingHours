package ru.egupov.accountingworkinghours.util.mapper;


import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.egupov.accountingworkinghours.dto.EventWorkDTO;
import ru.egupov.accountingworkinghours.model.EventType;
import ru.egupov.accountingworkinghours.model.EventWork;
import ru.egupov.accountingworkinghours.service.EmployeesService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EventWorkMapper implements Mapper<EventWork, EventWorkDTO>{

    private final ModelMapper modelMapper;
    private final EmployeesService employeesService;

    public EventWorkMapper(ModelMapper modelMapper, EmployeesService employeesService) {
        this.modelMapper = modelMapper;
        this.employeesService = employeesService;
    }

    @Override
    public EventWork toEntity(EventWorkDTO dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, EventWork.class);
    }

    @Override
    public EventWorkDTO toDto(EventWork entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, EventWorkDTO.class);
    }

    public List<EventWorkDTO> toDto(List<EventWork> works) {
        return works.stream().map(x -> toDto(x)).collect(Collectors.toList());
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(EventWork.class, EventWorkDTO.class)
                .addMappings(m -> {m.skip(EventWorkDTO::setEmployeeId);
                                   m.skip(EventWorkDTO::setEventType);}).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(EventWorkDTO.class, EventWork.class)
                .addMappings(m -> {m.skip(EventWork::setEmployee);
                                   m.skip(EventWork::setEventType);}).setPostConverter(toEntityConverter());
    }


    Converter<EventWork, EventWorkDTO> toDtoConverter() {
        return context -> {
            EventWork source = context.getSource();
            EventWorkDTO destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<EventWorkDTO, EventWork> toEntityConverter() {
        return context -> {
            EventWorkDTO source = context.getSource();
            EventWork destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(EventWork source, EventWorkDTO destination) {
        destination.setEmployeeId(source.getEmployee().getId());
        destination.setEventType(source.getEventType().toString());
    }

    void mapSpecificFields(EventWorkDTO source, EventWork destination) {
        destination.setEmployee(employeesService.findById(source.getEmployeeId()));
        destination.setEventType(EventType.valueOf(source.getEventType()));
    }
}
