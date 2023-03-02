package ru.egupov.accountingworkinghours.controller;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egupov.accountingworkinghours.dto.EmployeeDto;
import ru.egupov.accountingworkinghours.dto.EventWorkDTO;
import ru.egupov.accountingworkinghours.service.EventWorkService;
import ru.egupov.accountingworkinghours.util.ErrorResponse;
import ru.egupov.accountingworkinghours.util.error.DepartmentNotFoundException;
import ru.egupov.accountingworkinghours.util.error.EmployeeNotCreatedException;
import ru.egupov.accountingworkinghours.util.error.EmployeeNotFoundException;
import ru.egupov.accountingworkinghours.util.error.EventWorkNotCreatedException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventWorkRestController {

    private final EventWorkService eventWorkService;

    public EventWorkRestController(EventWorkService eventWorkService) {
        this.eventWorkService = eventWorkService;
    }

    @GetMapping("/list")
    public List<EventWorkDTO> getEvents(@RequestParam(required = false, name = "employee_id") Integer employeeId,
                                        @RequestParam(name = "date_start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,
                                        @RequestParam(name = "date_end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd){
        return eventWorkService.findByEmployeeIdDateStartDateEndInDto(employeeId, dateStart, dateEnd);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewEvent(@RequestBody @Valid EventWorkDTO eventWorkDTO,
                                                  BindingResult bindingResult){
        validateDto(eventWorkDTO, bindingResult);
        eventWorkService.saveFromDto(eventWorkDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") int id){
        eventWorkService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void validateDto(EventWorkDTO eventWorkDTO, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            StringBuilder errMsg = new StringBuilder();

            bindingResult.getFieldErrors().forEach(x ->
                    errMsg.append(x.getField())
                            .append(" - ").append(x.getDefaultMessage())
                            .append(";")

            );
            throw new EventWorkNotCreatedException(errMsg.toString());
        }
    }

    @ExceptionHandler({EventWorkNotCreatedException.class})
    public ResponseEntity<ErrorResponse> handleException(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
