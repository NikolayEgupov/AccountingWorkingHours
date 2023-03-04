package ru.egupov.accountingworkinghours.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_birth")
    private Date dateOfBirth;

    @Column(name = "email")
    private String email;
    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<EventWork> eventWorks;

    public Employee(String name, Date dateOfBirth, String email, String position, Department department, List<EventWork> eventWorks) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.position = position;
        this.department = department;
        this.eventWorks = eventWorks;
    }

}
