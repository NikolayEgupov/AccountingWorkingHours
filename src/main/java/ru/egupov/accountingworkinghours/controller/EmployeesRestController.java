package ru.egupov.accountingworkinghours.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.service.EmployeesService;
import ru.egupov.accountingworkinghours.util.ErrorResponse;
import ru.egupov.accountingworkinghours.util.error.DepartmentNotFoundException;
import ru.egupov.accountingworkinghours.util.error.EmployeeNotCreatedException;
import ru.egupov.accountingworkinghours.util.error.EmployeeNotFoundException;
import ru.egupov.accountingworkinghours.util.mapper.EmployeeMapper;
import ru.egupov.accountingworkinghours.util.validator.EmployeeValidator;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
public class EmployeesRestController {

    private final EmployeesService employeesService;
    private final EmployeeValidator employeeValidator;


    public EmployeesRestController(EmployeesService employeesService, EmployeeValidator employeeValidator) {
        this.employeesService = employeesService;
        this.employeeValidator = employeeValidator;
    }

    @GetMapping("/list")
    public List<EmployeeDto> getEmployees(@RequestParam(required = false, name = "department_id") Integer departmentId){
        return employeesService.findAllInDtoByParam(departmentId);
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable("id") int id){
        return employeesService.findByIdInDto(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid EmployeeDto employeeDto,
                                          BindingResult bindingResult){

        validateDto(employeeDto, bindingResult);

        employeesService.saveFromDto(employeeDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid EmployeeDto employeeDto,
                                             BindingResult bindingResult){

        employeeDto.setId(id);
        validateDto(employeeDto, bindingResult);

        employeesService.saveFromDto(employeeDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        employeesService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler({EmployeeNotCreatedException.class, EmployeeNotFoundException.class, DepartmentNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private void validateDto(EmployeeDto employeeDto, BindingResult bindingResult){
        employeeValidator.validate(employeeDto, bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder errMsg = new StringBuilder();

            bindingResult.getFieldErrors().forEach(x ->
                    errMsg.append(x.getField())
                            .append(" - ").append(x.getDefaultMessage())
                            .append(";")

            );
            throw new EmployeeNotCreatedException(errMsg.toString());
        }
    }

}
