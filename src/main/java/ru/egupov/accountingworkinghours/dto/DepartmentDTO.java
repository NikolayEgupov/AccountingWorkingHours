package ru.egupov.accountingworkinghours.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private int id;

    @NotEmpty(message = "Поле Наименование (name) должно быть заполнено")
    private String name;
}
