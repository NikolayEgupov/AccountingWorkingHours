package ru.egupov.accountingworkinghours.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.service.EmployeesService;

@Component
public class EmployeeValidator implements Validator {

    private final EmployeesService employeesService;

    public EmployeeValidator(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDto employee = (EmployeeDto) target;

        try {
            Employee findEmployee = employeesService.findByEmail(employee.getEmail()).get(0);

            if (employee.getId() == 0 || findEmployee.getId() != employee.getId()){
                errors.rejectValue("email", "",
                        "Сотрудник с такой электронной почтой уже существует");
            }
        } catch (IndexOutOfBoundsException ignored) {

        }

    }
}