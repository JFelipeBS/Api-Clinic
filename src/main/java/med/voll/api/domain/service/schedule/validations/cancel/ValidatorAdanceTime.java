package med.voll.api.domain.service.schedule.validations.cancel;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.schedule.ScheduleCancel;
import med.voll.api.domain.entities.ScheduleEntity;
import med.voll.api.domain.repository.ScheduleRepository;
import med.voll.api.infra.exceptions.ValidationExeption;

@Component
public class ValidatorAdanceTime implements ValidatorCancelScheduleInterface {

    @Autowired
    private ScheduleRepository repository;

    @Override
    public void validator(ScheduleCancel data) {

        ScheduleEntity scheduleEntity = repository.getReferenceById(data.idSchedule());
        LocalDateTime now = LocalDateTime.now();
        var differenceMinute = Duration.between(now, scheduleEntity.getDate()).toHours();

        if (differenceMinute < 24) {

            throw new ValidationExeption("Minimum 24 hours advance notice");

        }

    }

}