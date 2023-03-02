package ru.egupov.accountingworkinghours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import ru.egupov.accountingworkinghours.model.EventType;
import ru.egupov.accountingworkinghours.model.EventWork;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventWorkRepository extends JpaRepository<EventWork, Integer> {
    @Query("select e from EventWork e where (e.employee.id = :employee_id or :employee_id is null) and e.date between :dateStart and :dateEnd")
    List<EventWork> findByEmployee_IdAndDateBetween(@Param("employee_id") Integer employeeId,
                                                    @Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd);
}