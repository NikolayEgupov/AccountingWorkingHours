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

    /**
     * Метод для получения списка всех сотрудников
     * @return список найденных сотрудников
     */
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    /**
     * Метод для поиска всех сотрудников с определённым адресом электронной почты
     * @param email проверяемая почта
     * @return список найденных сотрудников
     */
    public List<Employee> findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    /**
     * Метод для поиска экземпляра сотрудника по его id
     * @param id id сотрудника
     * @return найденный экземпляр сотрудника
     */
    public Employee findById(int id){
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * Метод для сохранения экземпляра сотрудника
     * @param employee экземпляр Employee
     */
    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    /**
     * Метод для сохранения экземпляра сотрудника из DTO
     * @param employeeDto экземпляр EmployeeDto
     */
    public void saveFromDto(EmployeeDto employeeDto){
        save(employeeMapper.toEntity(employeeDto));
    }

    /**
     * Метод для удаления экземпляра сотрудника по id
     * @param id id сотрудника
     */
    public void deleteById(int id){

        Employee employee = findById(id);

        if (Objects.isNull(employee))
            throw new EmployeeNotFoundException("Сотрудник не найден");

        employeeRepository.deleteById(id);
    }

}
