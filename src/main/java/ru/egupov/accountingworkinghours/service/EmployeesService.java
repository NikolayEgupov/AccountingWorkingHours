package ru.egupov.accountingworkinghours.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.repository.EmployeeRepository;
import ru.egupov.accountingworkinghours.util.error.EmployeeNotFoundException;
import ru.egupov.accountingworkinghours.util.mapper.EmployeeMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentsService departmentsService;

    public List<EmployeeDto> getAllByParamInDto(Integer departmentId, String name, String email){
        Department department = departmentId != null ? departmentsService.getById(departmentId) : null;
        return employeeMapper.toDto(getByParam(department, name, email));
    }

    public List<Employee> getByParam(Department department, String name, String email){
        return employeeRepository.findByParam(department, name, email);
    }

    public EmployeeDto getByIdInDto(int id){
        return employeeMapper.toDto(getById(id));
    }

    public Employee getById(int id){
        return employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("The employee was not found by this id."));
    }

    public void updateFromDto(EmployeeDto employeeDto, int id){
        employeeDto.setId(id);
        save(employeeMapper.toEntity(employeeDto));
    }

    public void saveFromDto(EmployeeDto employeeDto){
        save(employeeMapper.toEntity(employeeDto));
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteById(int id){
        Employee employee = getById(id);
        employeeRepository.deleteById(id);
    }

}
