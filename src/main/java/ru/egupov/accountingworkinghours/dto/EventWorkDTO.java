package ru.egupov.accountingworkinghours.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.model.EventType;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventWorkDTO {

    private int id;

    @NotNull(message = "Поле employeeId должно быть заполнено")
    private int employeeId;

    private Date date;

    @NotNull(message = "Поле eventType должно быть заполнено")
    private String eventType;

}
