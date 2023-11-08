package med.voll.api.domain.service.schedule.validations;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.schedule.ScheduleDto;
import med.voll.api.infra.exceptions.ValidationExeption;

@Component
public class ValidatorClinicalOpeningHours implements ValidatorScheduleInterface {
    public void validator(ScheduleDto data) {

        LocalDateTime scheduleDate = data.date();

        boolean sunday = scheduleDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpening = scheduleDate.getHour() < 7;
        boolean afterOpening = scheduleDate.getHour() > 18;

        if (sunday || beforeOpening || afterOpening) {

            throw new ValidationExeption("Outside clinic opening hours");

        }
    }
}
