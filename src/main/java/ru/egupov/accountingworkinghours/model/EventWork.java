package ru.egupov.accountingworkinghours.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "event_work")
@Getter
@Setter
@NoArgsConstructor
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

    public EventWork(Employee employee, Date date, EventType eventType) {
        this.employee = employee;
        this.date = date;
        this.eventType = eventType;
    }

}
