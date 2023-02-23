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
    @NotEmpty(message = "Поле ФИО (name) должно быть заполнено")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_birth")
    @NotNull(message = "Поле Дата рождения (dateOfBirth) должно быть заполнено")
    private Date dateOfBirth;

    @Column(name = "email")
    @NotEmpty(message = "Поле Электронная почта (email) должно быть заполнено")
    @Email(message = "Адрес электронной почты (email) указан некорректно")
    private String email;

    @Column(name = "by_time_keeper")
    private boolean byTimeKeeper;

    public Employee() {
    }

    public Employee(String name, Date dateOfBirth, String email, boolean byTimeKeeper) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.byTimeKeeper = byTimeKeeper;
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
}