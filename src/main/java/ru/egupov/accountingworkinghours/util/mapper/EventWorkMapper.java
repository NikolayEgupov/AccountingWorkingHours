package ru.egupov.accountingworkinghours.util.mapper;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EventWorkMapper implements MapperImpl<EventWork, EventWorkDTO>{

    private final ModelMapper modelMapper;
    private final EmployeesService employeesService;

    @Override
    public List<EventWork> toEntity(List<EventWorkDTO> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public EventWork toEntity(EventWorkDTO dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, EventWork.class);
    }

    @Override
    public List<EventWorkDTO> toDto(List<EventWork> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public EventWorkDTO toDto(EventWork entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, EventWorkDTO.class);
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

    private Converter<EventWork, EventWorkDTO> toDtoConverter() {
        return context -> {
            EventWork source = context.getSource();
            EventWorkDTO destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private Converter<EventWorkDTO, EventWork> toEntityConverter() {
        return context -> {
            EventWorkDTO source = context.getSource();
            EventWork destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(EventWork source, EventWorkDTO destination) {
        destination.setEmployeeId(source.getEmployee().getId());
        destination.setEventType(source.getEventType().toString());
    }

    private void mapSpecificFields(EventWorkDTO source, EventWork destination) {
        destination.setEmployee(employeesService.getById(source.getEmployeeId()));
        destination.setEventType(EventType.valueOf(source.getEventType()));
    }
}
