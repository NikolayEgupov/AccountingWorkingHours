package ru.egupov.accountingworkinghours.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "employee")
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

    @Column(name = "by_time_keeper")
    private boolean byTimeKeeper;

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    public Employee() {
    }

    public Employee(String name, Date dateOfBirth, String email, boolean byTimeKeeper, String position, Department department) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.byTimeKeeper = byTimeKeeper;
        this.position = position;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isByTimeKeeper() {
        return byTimeKeeper;
    }

    public void setByTimeKeeper(boolean byTimeKeeper) {
        this.byTimeKeeper = byTimeKeeper;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
