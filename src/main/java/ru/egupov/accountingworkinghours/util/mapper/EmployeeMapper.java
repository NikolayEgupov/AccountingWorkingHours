package ru.egupov.accountingworkinghours.util.mapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.service.DepartmentsService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeMapper implements MapperImpl<Employee, EmployeeDto>{

    private final ModelMapper modelMapper;
    private final DepartmentsService departmentsService;

    @Override
    public List<Employee> toEntity(List<EmployeeDto> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Employee toEntity(EmployeeDto employeeDto){
        return Objects.isNull(employeeDto) ? null : modelMapper.map(employeeDto, Employee.class);
    }

    @Override
    public List<EmployeeDto> toDto(List<Employee> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto toDto(Employee employee){
        return Objects.isNull(employee) ? null : modelMapper.map(employee, EmployeeDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Employee.class, EmployeeDto.class)
                .addMappings(m -> m.skip(EmployeeDto::setDepartmentId)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(EmployeeDto.class, Employee.class)
                .addMappings(m -> m.skip(Employee::setDepartment)).setPostConverter(toEntityConverter());
    }

    private Converter<Employee, EmployeeDto> toDtoConverter() {
        return context -> {
            Employee source = context.getSource();
            EmployeeDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private Converter<EmployeeDto, Employee> toEntityConverter() {
        return context -> {
            EmployeeDto source = context.getSource();
            Employee destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(Employee source, EmployeeDto destination) {
        destination.setDepartmentId(getDepartmentId(source));
    }

    void mapSpecificFields(EmployeeDto source, Employee destination) {
        if (source.getDepartmentId() != null)
            destination.setDepartment(departmentsService.getById(source.getDepartmentId()));
    }

    private Integer getDepartmentId(Employee source) {
        Integer dep_id = null;
        if (!Objects.isNull(source) && !Objects.isNull(source.getDepartment()))
            dep_id = source.getDepartment().getId();
        return dep_id;
    }
}
