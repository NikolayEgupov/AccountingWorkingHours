package ru.egupov.accountingworkinghours.util.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.service.DepartmentsService;

import java.util.Objects;

@Component
public class EmployeeMapper implements Mapper<Employee, EmployeeDto>{

    private final ModelMapper modelMapper;
    private final DepartmentsService departmentsService;

    public EmployeeMapper(ModelMapper modelMapper, DepartmentsService departmentsService) {
        this.modelMapper = modelMapper;
        this.departmentsService = departmentsService;
    }

    @Override
    public Employee toEntity(EmployeeDto employeeDto){
        return Objects.isNull(employeeDto) ? null : modelMapper.map(employeeDto, Employee.class);
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

    Converter<Employee, EmployeeDto> toDtoConverter() {
        return context -> {
            Employee source = context.getSource();
            EmployeeDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<EmployeeDto, Employee> toEntityConverter() {
        return context -> {
            EmployeeDto source = context.getSource();
            Employee destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private Integer getId(Employee source) {
        Integer dep_id = null;
        if (!Objects.isNull(source) && !Objects.isNull(source.getDepartment()))
            dep_id = source.getDepartment().getId();
        return dep_id;
    }

    void mapSpecificFields(Employee source, EmployeeDto destination) {
        destination.setDepartmentId(getId(source));
    }

    void mapSpecificFields(EmployeeDto source, Employee destination) {
        if (source.getDepartmentId() != null)
            destination.setDepartment(departmentsService.findById(source.getDepartmentId()));
    }
}
