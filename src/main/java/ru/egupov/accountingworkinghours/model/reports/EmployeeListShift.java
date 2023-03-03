package ru.egupov.accountingworkinghours.model.reports;

import java.util.List;

public class EmployeeListShift {

    private Integer employee_id;

    private List<Shift> shifts;

    public EmployeeListShift() {
    }

    public EmployeeListShift(Integer employee_id, List<Shift> shifts) {
        this.employee_id = employee_id;
        this.shifts = shifts;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}
