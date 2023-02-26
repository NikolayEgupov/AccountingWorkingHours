package ru.egupov.accountingworkinghours.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egupov.accountingworkinghours.dto.DepartmentDTO;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.service.DepartmentsService;
import ru.egupov.accountingworkinghours.util.ErrorResponse;
import ru.egupov.accountingworkinghours.util.error.DepartmentNotCreatedException;
import ru.egupov.accountingworkinghours.util.error.DepartmentNotFoundException;
import ru.egupov.accountingworkinghours.util.error.EmployeeNotCreatedException;
import ru.egupov.accountingworkinghours.util.error.EmployeeNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentsRestController {

    private final DepartmentsService departmentsService;

    public DepartmentsRestController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping("/list")
    public List<DepartmentDTO> getDepartment(){
        return departmentsService.findAllInDto();
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable("id") int id){
        return departmentsService.findByIdInDto(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addDepartment(@RequestBody @Valid DepartmentDTO departmentDTO,
                                                    BindingResult bindingResult){
        validateDto(departmentDTO, bindingResult);
        departmentsService.saveFromDto(departmentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<HttpStatus> updateDepartment(@PathVariable("id") int id,
                                                       @RequestBody @Valid DepartmentDTO departmentDTO,
                                                       BindingResult bindingResult){
        departmentDTO.setId(id);
        validateDto(departmentDTO, bindingResult);
        departmentsService.saveFromDto(departmentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") int id){
        departmentsService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler({DepartmentNotFoundException.class, DepartmentNotCreatedException.class})
    public ResponseEntity<ErrorResponse> handleException(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private void validateDto(DepartmentDTO departmentDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errMsg = new StringBuilder();

            bindingResult.getFieldErrors().forEach(x ->
                    errMsg.append(x.getField())
                            .append(" - ").append(x.getDefaultMessage())
                            .append(";")

            );
            throw new DepartmentNotCreatedException(errMsg.toString());
        }
    }
}
