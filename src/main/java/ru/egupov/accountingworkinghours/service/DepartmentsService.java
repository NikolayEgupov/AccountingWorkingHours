package ru.egupov.accountingworkinghours.service;

import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.dto.DepartmentDTO;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.repository.DepartmentRepository;
import ru.egupov.accountingworkinghours.util.error.DepartmentNotFoundException;
import ru.egupov.accountingworkinghours.util.mapper.DepartmentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentsService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;


    public DepartmentsService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public List<DepartmentDTO> findAllInDto(){
        return convertToListDto(findAll());
    }

    public Department findById(int id){
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Департамент с таким id не найден"));
    }

    public DepartmentDTO findByIdInDto(int id){
        return convertToDto(findById(id));
    }

    public void save(Department department){
        departmentRepository.save(department);
    }

    public void saveFromDto(DepartmentDTO departmentDTO){
        save(departmentMapper.toEntity(departmentDTO));
    }

    public void deleteById(int id){
        departmentRepository.deleteById(id);
    }

    private List<DepartmentDTO> convertToListDto(List<Department> departments){
        return departments.stream().map(departmentMapper::toDto).collect(Collectors.toList());
    }

    private DepartmentDTO convertToDto(Department department){
        return departmentMapper.toDto(department);
    }
}
