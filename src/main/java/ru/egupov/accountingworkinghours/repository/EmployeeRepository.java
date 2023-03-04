package ru.egupov.accountingworkinghours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egupov.accountingworkinghours.model.Department;
import ru.egupov.accountingworkinghours.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByEmail(String email);
    List<Employee> findByDepartment(Department department);
    @Query("select e from Employee e where (e.department = :department or ?1 is null)" +
            "and (e.name = :name or :name is null) and (e.email = :email or :email is null)")
    List<Employee> findByParam(@Param("department") Department department, @Param("name") String name,
                               @Param("email") String email);
}