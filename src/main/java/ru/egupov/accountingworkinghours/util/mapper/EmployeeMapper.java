package ru.egupov.accountingworkinghours.util.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Employee;

import java.util.Objects;

@Component
public class EmployeeMapper {

    private final ModelMapper modelMapper;

    public EmployeeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Employee toEntity(EmployeeDto employeeDto){
        return Objects.isNull(employeeDto) ? null : modelMapper.map(employeeDto, Employee.class);
    }

    public EmployeeDto toDto(Employee employee){
        return Objects.isNull(employee) ? null : modelMapper.map(employee, EmployeeDto.class);
    }
}
