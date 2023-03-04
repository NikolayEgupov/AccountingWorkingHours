package ru.egupov.accountingworkinghours.util.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.egupov.accountingworkinghours.dto.DepartmentDTO;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.model.Employee;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentMapper implements MapperImpl<Department, DepartmentDTO> {

    private final ModelMapper modelMapper;

    @Override
    public List<Department> toEntity(List<DepartmentDTO> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Department toEntity(DepartmentDTO dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Department.class);
    }

    @Override
    public List<DepartmentDTO> toDto(List<Department> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO toDto(Department entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, DepartmentDTO.class);
    }
}
