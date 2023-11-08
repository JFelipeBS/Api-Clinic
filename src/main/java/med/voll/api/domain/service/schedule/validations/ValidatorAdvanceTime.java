package med.voll.api.domain.service.schedule.validations;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.schedule.ScheduleDto;
import med.voll.api.infra.exceptions.ValidationExeption;

@Component
public class ValidatorAdvanceTime implements ValidatorScheduleInterface{
    public void validator(ScheduleDto data) {

        LocalDateTime scheduleDate = data.date();
        LocalDateTime now = LocalDateTime.now();
        var differenceMinute = Duration.between(now,scheduleDate).toMillis();

        if (differenceMinute < 30) {
            throw new ValidationExeption("minimum 30 minutes");
        }
        
    }

}
