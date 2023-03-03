package ru.egupov.accountingworkinghours.service;

import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.model.reports.EmployeeListShift;
import ru.egupov.accountingworkinghours.util.error.reports.IncorrectDateException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportsService {

    private final EmployeesService employeesService;
    private final DepartmentsService departmentsService;
    private final ShiftService shiftService;

    public ReportsService(EmployeesService employeesService, DepartmentsService departmentsService, EventsWorkService eventsWorkService, ShiftService shiftService) {
        this.employeesService = employeesService;
        this.departmentsService = departmentsService;
        this.shiftService = shiftService;
    }

    public List<EmployeeListShift> departmentShiftReportByDepartmentId(int departmentId,
                                                                       Date dateStart, Date dateEnd){
        return departmentShiftReport(departmentsService.findById(departmentId), dateStart, dateEnd);
    }

    public List<EmployeeListShift> departmentShiftReport(Department department,
                                                         Date dateStart, Date dateEnd) {
        List<EmployeeListShift> employeesListShifts = new ArrayList<>();

        for (Employee emp: department.getEmployees()) {
            employeesListShifts.add(employeeListShiftReport(emp, dateStart, dateEnd));
        }

        return employeesListShifts;
    }

    public EmployeeListShift employeeListShiftReportByEmployeeId(Integer employeeId,
                                                                 Date dateStart, Date dateEnd) {
        return employeeListShiftReport(employeesService.findById(employeeId), dateStart, dateEnd);
    }

    public EmployeeListShift employeeListShiftReport(Employee employee,
                                                     Date dateStart, Date dateEnd) {
        if (!dateStart.before(dateEnd))
            throw new IncorrectDateException("Некорректные даты отчета! Дата начала должна быть меньше даты окончания.");

        EmployeeListShift employeeListShift = new EmployeeListShift();
        employeeListShift.setEmployee_id(employee.getId());
        employeeListShift.setShifts(shiftService.getShiftsOnPeriod(employee, dateStart, dateEnd));

        return employeeListShift;
    }

}
