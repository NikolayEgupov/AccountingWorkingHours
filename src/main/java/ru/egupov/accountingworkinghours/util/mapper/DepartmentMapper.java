package ru.egupov.accountingworkinghours.util.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.egupov.accountingworkinghours.dto.DepartmentDTO;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.model.Employee;

import java.util.Objects;

@Component
public class DepartmentMapper implements Mapper<Department, DepartmentDTO> {

    private final ModelMapper modelMapper;

    public DepartmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Department toEntity(DepartmentDTO dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Department.class);
    }

    @Override
    public DepartmentDTO toDto(Department entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, DepartmentDTO.class);
    }
}
