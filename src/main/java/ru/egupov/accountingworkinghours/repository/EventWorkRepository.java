package ru.egupov.accountingworkinghours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import ru.egupov.accountingworkinghours.model.EventType;
import ru.egupov.accountingworkinghours.model.EventWork;

import java.util.Optional;

public interface EventWorkRepository extends JpaRepository<EventWork, Integer> {
}