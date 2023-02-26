package ru.egupov.accountingworkinghours.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.model.EventType;

import java.util.Date;

public class EventWorkDTO {

    private int id;

    @NotNull(message = "Поле employeeId должно быть заполнено")
    private Integer employeeId;

    private Date date;

    @NotNull(message = "Поле eventType должно быть заполнено")
    private String eventType;

    public EventWorkDTO() {
    }

    public EventWorkDTO(Integer employeeId, Date date, String eventType) {
        this.employeeId = employeeId;
        this.date = date;
        this.eventType = eventType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
