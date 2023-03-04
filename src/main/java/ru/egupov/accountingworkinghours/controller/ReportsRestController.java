package ru.egupov.accountingworkinghours.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.egupov.accountingworkinghours.model.reports.EmployeeListShift;
import ru.egupov.accountingworkinghours.service.ReportsService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportsRestController {

    private final ReportsService reportsService;

    @GetMapping("/employee_shifts")
    public EmployeeListShift getEmployeeShiftsReport(@RequestParam(name = "employee_id") int employeeId,
                                                     @RequestParam(name = "date_start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,
                                                     @RequestParam(name = "date_end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd){
        return reportsService.employeeListShiftReportByEmployeeId(employeeId, dateStart, dateEnd);
    }

    @GetMapping("/department_shifts")
    public List<EmployeeListShift> getDepartmentShiftsReport(@RequestParam(name = "department_id") int departmentId,
                                                             @RequestParam(name = "date_start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,
                                                             @RequestParam(name = "date_end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd){
        return reportsService.departmentShiftReportByDepartmentId(departmentId, dateStart, dateEnd);
    }

}
