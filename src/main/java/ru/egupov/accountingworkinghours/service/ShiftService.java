package ru.egupov.accountingworkinghours.service;

import org.springframework.stereotype.Service;
import ru.egupov.accountingworkinghours.model.Employee;
import ru.egupov.accountingworkinghours.model.EventType;
import ru.egupov.accountingworkinghours.model.EventWork;
import ru.egupov.accountingworkinghours.model.reports.Shift;
import ru.egupov.accountingworkinghours.util.DateTools;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShiftService {

    private final EventsWorkService eventsWorkService;

    public ShiftService(EventsWorkService eventsWorkService) {
        this.eventsWorkService = eventsWorkService;
    }

    public List<Shift> getShiftsOnPeriod(Employee employee, Date dateStart, Date dateEnd){

        List<Shift> retShifts = new ArrayList<>();

        List<EventWork> eventWorks = eventsWorkService.findByEmployeeAndDateBetweenOrderByDate(employee, dateStart, dateEnd);

        for (int i = 0; i < ChronoUnit.DAYS.between(dateStart.toInstant(), dateEnd.toInstant()); i++) {
            Date dtStartInIter = new Date(dateStart.getTime() + (24L * 60 * 60 * 1000 * i - 1));
            Date dtEndInIter = new Date(dtStartInIter.getTime() + (24L * 60 * 60 * 1000 + 1));
            List<EventWork>  eventWorksOnDays = eventWorks.stream()
                    .filter(x -> x.getDate().after(dtStartInIter) && x.getDate().before(dtEndInIter)).toList();
            if (eventWorksOnDays.isEmpty())
                continue;
            retShifts.addAll(calcShiftOnDay(eventWorksOnDays));
        }

        return retShifts;
    }

    public List<Shift> calcShiftOnDay(List<EventWork> eventWorksOnDays){

        List<Shift> shiftList = new ArrayList<>();

        for (int j = 0; j < eventWorksOnDays.size(); j++){

            Shift shiftEvent = null;

            if (eventWorksOnDays.get(j).getEventType() == EventType.END_WORK){
                if (j == 0) {
                    shiftEvent = new Shift();
                    shiftEvent.setDateStart(
                            DateTools.getStartDay(eventWorksOnDays.get(j).getDate())
                    );
                    shiftEvent.setDateEnd(eventWorksOnDays.get(j).getDate());
                    shiftEvent.setEventEndId(eventWorksOnDays.get(j).getId());
                }
            } else if (eventWorksOnDays.get(j).getEventType() == EventType.START_WORK){
                shiftEvent = new Shift();
                shiftEvent.setDateStart(eventWorksOnDays.get(j).getDate());
                shiftEvent.setEventStartId(eventWorksOnDays.get(j).getId());

                if (j+1 < eventWorksOnDays.size()) {
                    shiftEvent.setDateEnd(eventWorksOnDays.get(j+1).getDate());
                    shiftEvent.setEventEndId(eventWorksOnDays.get(j+1).getId());
                } else {
                    shiftEvent.setDateEnd(DateTools.getEndDay(eventWorksOnDays.get(j).getDate()));
                }
            }

            if (shiftEvent != null){
                shiftEvent.setAmountTimeMinute(
                        (int) ChronoUnit.MINUTES.between(shiftEvent.getDateStart().toInstant(),
                                shiftEvent.getDateEnd().toInstant()));
                shiftList.add(shiftEvent);
            }

        }

        return shiftList;
    }

}
