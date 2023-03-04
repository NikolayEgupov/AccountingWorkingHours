package ru.egupov.accountingworkinghours.model.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListShift {
    private Integer employee_id;
    private List<Shift> shifts;
}
