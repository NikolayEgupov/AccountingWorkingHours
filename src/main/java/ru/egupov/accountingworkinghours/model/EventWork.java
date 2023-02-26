package ru.egupov.accountingworkinghours.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "event_work")
public class EventWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_event")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    public EventWork() {
    }

    public EventWork(Employee employee, Date date, EventType eventType) {
        this.employee = employee;
        this.date = date;
        this.eventType = eventType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
