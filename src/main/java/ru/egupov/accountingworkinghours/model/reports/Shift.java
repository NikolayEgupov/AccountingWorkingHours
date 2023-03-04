package ru.egupov.accountingworkinghours.model.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    private Date dateStart;
    private Date dateEnd;
    private Integer eventStartId;
    private Integer eventEndId;
    private Integer amountTimeMinute;
}
