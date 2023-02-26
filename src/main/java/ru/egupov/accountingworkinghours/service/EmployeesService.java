package ru.egupov.accountingworkinghours.service;

import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.repository.EmployeeRepository;
import ru.egupov.accountingworkinghours.util.error.EmployeeNotFoundException;
import ru.egupov.accountingworkinghours.util.mapper.EmployeeMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private final DepartmentsService departmentsService;

    public EmployeesService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, DepartmentsService departmentsService) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.departmentsService = departmentsService;
    }

    /**
     * Метод для получения списка всех сотрудников
     * @return список найденных сотрудников в формате DTO
     */
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public List<Employee> findByDepartment(Department department){
        return employeeRepository.findByDepartment(department);
    }

    /**
     * Метод для получения списка сотрудников в зависимости от параметров поиска
     * @param departmentId - id департамента. При отсутствии такого департамента в базе - вернёт пустой список.
     * @return список сотрудников в формате EmployeeDto
     */
    public List<EmployeeDto> findAllInDtoByParam(Integer departmentId){

        List<EmployeeDto> employeeDto = null;

        if (departmentId != null){
            Department department = new Department();
            department.setId(departmentId);
            employeeDto = convertToListDto(findByDepartment(department));
        } else {
            employeeDto = convertToListDto(findAll());
        }

        return employeeDto;
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
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public EmployeeDto findByIdInDto(int id){
        return convertToDto(findById(id));
    }

    /**
     * Метод для поиска всех экземпляров сотрудников по id департамента
     * @param departmentId id департамента
     * @return список найденных сотрудников в формате DTO
     */
    public List<EmployeeDto> findByDepartmentIdInDto (int departmentId){
        Department department = departmentsService.findById(departmentId);
        return convertToListDto(employeeRepository.findByDepartment(department));
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

    public List<EmployeeDto> convertToListDto(List<Employee> employees){
        return employees.stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    public EmployeeDto convertToDto(Employee employee){
        return employeeMapper.toDto(employee);
    }

}
