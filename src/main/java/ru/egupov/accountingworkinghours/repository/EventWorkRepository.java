package ru.egupov.accountingworkinghours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.model.EventType;
import ru.egupov.accountingworkinghours.model.EventWork;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventWorkRepository extends JpaRepository<EventWork, Integer> {
    List<EventWork> findByEmployeeAndDateBetweenOrderByDate(Employee employee, Date dateStart, Date dateEnd);
}