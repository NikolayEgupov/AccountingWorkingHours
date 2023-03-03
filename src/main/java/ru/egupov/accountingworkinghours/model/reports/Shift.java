package ru.egupov.accountingworkinghours.model.reports;

import java.util.Date;

public class Shift {

    private Date dateStart;
    private Date dateEnd;

    private Integer eventStartId;
    private Integer eventEndId;

    private Integer amountTimeMinute;

    public Shift(Date dateStart, Date dateEnd, Integer eventStartId, Integer eventEndId, Integer amountTimeMinute) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.eventStartId = eventStartId;
        this.eventEndId = eventEndId;
        this.amountTimeMinute = amountTimeMinute;
    }

    public Shift() {
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getEventStartId() {
        return eventStartId;
    }

    public void setEventStartId(Integer eventStartId) {
        this.eventStartId = eventStartId;
    }

    public Integer getEventEndId() {
        return eventEndId;
    }

    public void setEventEndId(Integer eventEndId) {
        this.eventEndId = eventEndId;
    }

    public Integer getAmountTimeMinute() {
        return amountTimeMinute;
    }

    public void setAmountTimeMinute(Integer amountTimeMinute) {
        this.amountTimeMinute = amountTimeMinute;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", eventStartId=" + eventStartId +
                ", eventEndId=" + eventEndId +
                ", amountTimeMinute=" + amountTimeMinute +
                '}';
    }
}
