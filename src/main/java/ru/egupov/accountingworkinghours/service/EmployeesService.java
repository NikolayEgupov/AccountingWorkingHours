package ru.egupov.accountingworkinghours.service;

import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.repository.EmployeeRepository;
import ru.egupov.accountingworkinghours.util.error.EmployeeNotFoundException;
import ru.egupov.accountingworkinghours.util.mapper.EmployeeMapper;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeesService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public List<Employee> findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    public Employee findById(int id){
        return employeeRepository.findById(id).orElse(null);
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public void saveFromDto(EmployeeDto employeeDto){
        save(employeeMapper.toEntity(employeeDto));
    }

    public void deleteById(int id){

        Employee employee = findById(id);

        if (Objects.isNull(employee))
            throw new EmployeeNotFoundException("Сотрудник не найден");

        employeeRepository.deleteById(id);
    }

}
