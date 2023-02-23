package ru.egupov.accountingworkinghours.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class EmployeeDto {

    private int id;

    @NotEmpty(message = "Поле ФИО (name) должно быть заполнено")
    private String name;

    @NotNull(message = "Поле Дата рождения (dateOfBirth) должно быть заполнено")
    private Date dateOfBirth;

    @NotEmpty(message = "Поле Электронная почта (email) должно быть заполнено")
    @Email(message = "Адрес электронной почты (email) указан некорректно")
    private String email;

    private boolean byTimeKeeper;

    public EmployeeDto() {
    }

    public EmployeeDto(String name, Date dateOfBirth, String email, boolean byTimeKeeper) {
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
