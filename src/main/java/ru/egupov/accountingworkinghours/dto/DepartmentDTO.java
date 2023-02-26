package ru.egupov.accountingworkinghours.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class DepartmentDTO {

    private int id;

    @NotEmpty(message = "Поле Наименование (name) должно быть заполнено")
    private String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String name) {
        this.name = name;
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
}
