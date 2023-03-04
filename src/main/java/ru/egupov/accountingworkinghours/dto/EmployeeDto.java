package ru.egupov.accountingworkinghours.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private int id;

    @NotEmpty(message = "Поле ФИО (name) должно быть заполнено")
    private String name;

    @NotNull(message = "Поле Дата рождения (dateOfBirth) должно быть заполнено")
    private Date dateOfBirth;

    @NotEmpty(message = "Поле Электронная почта (email) должно быть заполнено")
    @Email(message = "Адрес электронной почты (email) указан некорректно")
    private String email;

    private Integer departmentId;

    private String position;

}
