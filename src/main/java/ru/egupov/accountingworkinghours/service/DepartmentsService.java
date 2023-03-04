package ru.egupov.accountingworkinghours.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.dto.DepartmentDTO;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.repository.DepartmentRepository;
import ru.egupov.accountingworkinghours.util.error.DepartmentNotFoundException;
import ru.egupov.accountingworkinghours.util.mapper.DepartmentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentsService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public List<DepartmentDTO> getAllInDto(){
        return departmentMapper.toDto(getAll());
    }

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public DepartmentDTO getByIdInDto(int id){
        return departmentMapper.toDto(getById(id));
    }

    public Department getById(int id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("The department with this ID was not found."));
    }

    public void updateFromDto(DepartmentDTO departmentDTO, int id){
        departmentDTO.setId(id);
        save(departmentMapper.toEntity(departmentDTO));
    }

    public void saveFromDto(DepartmentDTO departmentDTO){
        save(departmentMapper.toEntity(departmentDTO));
    }

    public void save(Department department){
        departmentRepository.save(department);
    }

    public void deleteById(int id){
        departmentRepository.deleteById(id);
    }

}
