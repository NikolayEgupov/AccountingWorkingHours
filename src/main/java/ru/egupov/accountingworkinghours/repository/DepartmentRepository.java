package ru.egupov.accountingworkinghours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.egupov.accountingworkinghours.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
}