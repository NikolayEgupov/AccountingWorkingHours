package ru.egupov.accountingworkinghours.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.model.reports.EmployeeListShift;
import ru.egupov.accountingworkinghours.util.error.IncorrectDateReportException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportsService {

    private final EmployeesService employeesService;
    private final DepartmentsService departmentsService;
    private final ShiftService shiftService;

    public List<EmployeeListShift> departmentShiftReportByDepartmentId(int departmentId,
                                                                       Date dateStart, Date dateEnd){
        return departmentShiftReport(departmentsService.getById(departmentId), dateStart, dateEnd);
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
        return employeeListShiftReport(employeesService.getById(employeeId), dateStart, dateEnd);
    }

    public EmployeeListShift employeeListShiftReport(Employee employee,
                                                     Date dateStart, Date dateEnd) {
        if (!dateStart.before(dateEnd))
            throw new IncorrectDateReportException("Incorrect request dates. The start date must be less than the end date.");

        EmployeeListShift employeeListShift = new EmployeeListShift();
        employeeListShift.setEmployee_id(employee.getId());
        employeeListShift.setShifts(shiftService.getShiftsOnPeriod(employee, dateStart, dateEnd));

        return employeeListShift;
    }

}
